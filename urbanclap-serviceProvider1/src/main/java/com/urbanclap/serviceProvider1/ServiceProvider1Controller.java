package com.urbanclap.serviceProvider1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.urbanclap.serviceProvider1.beans.ProviderRequest;
import com.urbanclap.serviceProvider1.beans.ProviderResponse;

@RestController
public class ServiceProvider1Controller {

	Logger LOG = LoggerFactory.getLogger(ServiceProvider1Controller.class);

	@PostMapping(value = "/checkSlotAvailable", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProviderResponse takeBooking(@RequestBody ProviderRequest request) {
		LOG.info("Service provider is available.");
		return new ProviderResponse(Boolean.TRUE,"Malti",1234567);
	}
}
