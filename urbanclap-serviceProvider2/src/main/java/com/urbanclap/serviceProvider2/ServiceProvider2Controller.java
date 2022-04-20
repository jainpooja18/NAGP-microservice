package com.urbanclap.serviceProvider2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.urbanclap.serviceProvider2.beans.ProviderRequest;
import com.urbanclap.serviceProvider2.beans.ProviderResponse;

@RestController
public class ServiceProvider2Controller {
	Logger LOG = LoggerFactory.getLogger(ServiceProvider2Controller.class);


	@PostMapping(value = "/checkSlotAvailable", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProviderResponse takeBooking(@RequestBody ProviderRequest request) {
		LOG.info("No service provider available.");
		return new ProviderResponse(Boolean.FALSE, null, 0l);
	}
}
