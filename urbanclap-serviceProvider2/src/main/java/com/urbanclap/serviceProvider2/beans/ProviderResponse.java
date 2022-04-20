package com.urbanclap.serviceProvider2.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderResponse implements Serializable {

	private static final long serialVersionUID = -7327095835402183960L;

	private Boolean isBookingAccepted;
	private String nameOfServiceProvider;
	private long contactOfServiceProvider;
}
