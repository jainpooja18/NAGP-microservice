package com.urbanclap.login.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class LoginResponse implements Serializable {

	private static final long serialVersionUID = 6121351992637810305L;

	private String jwtToken;
	private String errorMessage;
}
