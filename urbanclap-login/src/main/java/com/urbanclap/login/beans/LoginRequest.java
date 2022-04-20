package com.urbanclap.login.beans;

import java.io.Serializable;

import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest implements Serializable {

	private static final long serialVersionUID = 5276867739817240396L;

	@NonNull
	private Long mobileNo;
	@NonNull
	private String password;

}
