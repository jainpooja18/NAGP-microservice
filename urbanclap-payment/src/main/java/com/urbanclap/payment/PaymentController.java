package com.urbanclap.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.urbanclap.payment.beans.PaymentRequest;

@RestController
public class PaymentController {

	Logger LOG = LoggerFactory.getLogger(PaymentController.class);

	@PostMapping(value="/payment", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> takePayment(@RequestBody PaymentRequest request){
		
		LOG.info("Payment service: Payment in progress...");
		//should redirect to third party(paytm,paypal,gpay etc) to take payment based on service availed
		return ResponseEntity.ok("Payment successfully done.");
	}
}
