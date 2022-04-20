package com.urbanclap.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urbanclap.booking.beans.BookingRequest;
import com.urbanclap.booking.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService service;

	@PostMapping(value = "/bookNewService", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> bookService(@RequestBody BookingRequest request,
			@RequestHeader("Authorization") String authorization) {

		if (service.validateJwt(authorization)) {
			String response = service.bookService(request);
			return ResponseEntity.ok(response);
		} else {
			return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
		}

	}

}
