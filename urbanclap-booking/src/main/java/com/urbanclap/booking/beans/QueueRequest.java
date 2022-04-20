package com.urbanclap.booking.beans;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class QueueRequest implements Serializable{

	private static final long serialVersionUID = -441389640770739827L;

	private AvailableServices typeOfService;
	private LocalDateTime timeOfService;
	private String areaOfService;
	private String mobileOFUser;
	private int noOfRetries;
	
}
