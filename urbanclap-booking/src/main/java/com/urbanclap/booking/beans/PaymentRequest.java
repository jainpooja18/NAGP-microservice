package com.urbanclap.booking.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest implements Serializable {

	private static final long serialVersionUID = 45778559959606780L;

	private AvailableServices typeOfService;
}
