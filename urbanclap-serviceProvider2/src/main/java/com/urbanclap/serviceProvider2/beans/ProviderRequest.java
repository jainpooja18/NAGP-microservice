package com.urbanclap.serviceProvider2.beans;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderRequest implements Serializable{

	private static final long serialVersionUID = -7313955012273006630L;

	private LocalDateTime timeOfService;
}
