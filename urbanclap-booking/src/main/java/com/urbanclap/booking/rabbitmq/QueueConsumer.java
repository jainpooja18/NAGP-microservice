package com.urbanclap.booking.rabbitmq;

import java.util.ArrayList;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.urbanclap.booking.beans.ProviderRequest;
import com.urbanclap.booking.beans.ProviderResponse;
import com.urbanclap.booking.beans.QueueRequest;

@Service
public class QueueConsumer {

	Logger LOG = LoggerFactory.getLogger(QueueConsumer.class);

	@Autowired
	private Environment env;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private RestTemplate restTemplate;

	@Value("${exchange.name}")
	private String exchange;

	@Value("${routekey.value}")
	private String routeKey;

	@RabbitListener(queues = { "${queue.name}" })
	public void readsQueue(@Payload QueueRequest body) {

		LOG.info("Booking service: Consumer reads booking request from queue.");
		LOG.info("Message: {}", body.toString());

		if (body.getNoOfRetries() >= 3) {
			LOG.info("Booking failed. All providers are occupied at the moment. Please try after sometime."); // this message to be send as SMS
			return;
		} else {
			String propertyToRead = "service.provider." + body.getTypeOfService().toString() + "."
					+ body.getAreaOfService().toUpperCase();
			Boolean isBookingAccepted = Boolean.FALSE;
			ProviderResponse response = null;
			ArrayList<Integer> alreadyTriedProviders = new ArrayList<>();
			String[] availableProviders = null;

			String listOfProviders = env.getProperty(propertyToRead);
			if (listOfProviders != null && !listOfProviders.isEmpty()) {
				availableProviders = listOfProviders.split(",");

				do {
					int choosingProviderRandomly = new Random().nextInt(availableProviders.length - 0) + 0;
					if (alreadyTriedProviders.contains(choosingProviderRandomly)) {
						continue;
					}
					String url = "http://" + availableProviders[choosingProviderRandomly] + "/checkSlotAvailable";
					response = restTemplate.postForObject(url, new ProviderRequest(body.getTimeOfService()),
							ProviderResponse.class);
					isBookingAccepted = response.getIsBookingAccepted();
					alreadyTriedProviders.add(choosingProviderRandomly);

					if (alreadyTriedProviders.size() == availableProviders.length && !isBookingAccepted) {
						body.setNoOfRetries(body.getNoOfRetries() + 1);
						rabbitTemplate.convertAndSend(exchange, routeKey, body);
						return;
					}

				} while (!isBookingAccepted);
			} else {
				LOG.info("Booking failed. All providers are occupied at the moment. Please try after sometime."); // this message to be send as SMS
				return;
			}
			if (isBookingAccepted) {
				// send details to consumer and provider on SMS
				LOG.info("Customer details: Customer's contact: {}, Area of service: {}, Time of srevice: {}",
						body.getMobileOFUser(), body.getAreaOfService(), body.getTimeOfService());
				LOG.info("Provider details: Service provider name: {}, service provider's contact: {}",
						response.getNameOfServiceProvider(), response.getContactOfServiceProvider());
			}
		}

	}
}
