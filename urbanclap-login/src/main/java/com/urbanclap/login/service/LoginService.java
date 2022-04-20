package com.urbanclap.login.service;

import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.urbanclap.login.beans.LoginRequest;
import com.urbanclap.login.beans.LoginResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginService {
	private static final String JWT_SECRET_KEY = "testKey";
	Logger LOG = LoggerFactory.getLogger(LoginService.class);
	HashMap<Long, String> registeredUsers = new HashMap<>();

	public void signUp(LoginRequest request) {
		registeredUsers.put(request.getMobileNo(), request.getPassword());
		LOG.info("Login service: User successfully registered");

	}

	public LoginResponse login(LoginRequest request) {
		LoginResponse response = new LoginResponse();
		if (!registeredUsers.containsKey(request.getMobileNo())
				|| !registeredUsers.get(request.getMobileNo()).equals(request.getPassword())) {
			response.setErrorMessage("Wrong mobile/passowrd. Please check again or signup first.");
			LOG.info("Login service: Invalid login credentials passed.");

		} else {
			HashMap<String, Object> claims = new HashMap<>();
			claims.put("mobile", request.getMobileNo().toString());
			claims.put("blocked", "No");
			String jwtToken = Jwts.builder().setClaims(claims).setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis() + 3600000))
					.signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY).compact();
			response.setJwtToken(jwtToken);
			LOG.info("Login service: JWT token successfully generated for user with mobile {}.", request.getMobileNo());
		}
		return response;
	}

}
