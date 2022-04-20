package com.urbanclap.booking.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.urbanclap.booking.beans.BookingRequest;
import com.urbanclap.booking.beans.PaymentRequest;
import com.urbanclap.booking.beans.QueueRequest;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class BookingService {

	Logger LOG = LoggerFactory.getLogger(BookingService.class);
	private static final String JWT_SECRET_KEY = "testKey";
	private String mobileOfCOnsumer;
	private String isConsumerBlocked;

	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private RestTemplate restTemplate;

	@Value("${exchange.name}")
	private String exchange;
	@Value("${routekey.value}")
	private String routeKey;

	@CircuitBreaker(name = "paymentService", fallbackMethod = "paymentServiceFallback")
	public String bookService(BookingRequest request) {

		LOG.info("Booking service: Booking request accepted.");
		QueueRequest queueEntry = new QueueRequest(request.getTypeOfService(), request.getTimeOfService(),
				request.getAreaOfService(), mobileOfCOnsumer, 0);

		LOG.info("Booking service: Booking request added to queue.");
		rabbitTemplate.convertAndSend(exchange, routeKey, queueEntry);

		LOG.info("Booking service: Calling payment service.");
		restTemplate.postForObject("http://PaymentService/payment", new PaymentRequest(request.getTypeOfService()),
				String.class);

		return "Your order for service is placed. You will get provider details on sms shortly. Thanks.";
	}

	public String paymentServiceFallback(Exception e) {

		LOG.info("Booking service: Payment service is down. Booking a COD order");
		return "Payment service is down. Order is placed as COD by default.";
	}

	public boolean validateJwt(String authorization) {
		boolean validated = false;
		if (authorization != null && !authorization.isEmpty() && !authorization.isBlank()
				&& authorization.startsWith("Bearer ")) {
			String jwt = authorization.substring(7);
			Claims claims = Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(jwt).getBody();
			Boolean isTokenExpired = claims.getExpiration().before(new Date());
			mobileOfCOnsumer = claims.get("mobile").toString();
			isConsumerBlocked = claims.get("blocked").toString();

			if (!isTokenExpired && isConsumerBlocked.equalsIgnoreCase("No")) {
				validated = true;
			}
		}
		return validated;
	}
}
