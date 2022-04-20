package com.urbanclap.booking.beans;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
public class BookingRequest implements Serializable{

	private static final long serialVersionUID = 2179663950620137509L;

	@NonNull
	private AvailableServices typeOfService;
	@NonNull
	private LocalDateTime timeOfService;
	@NonNull
	private String areaOfService;
}
