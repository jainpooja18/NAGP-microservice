package com.urbanclap.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urbanclap.login.beans.LoginRequest;
import com.urbanclap.login.beans.LoginResponse;
import com.urbanclap.login.service.LoginService;

@RestController
@RequestMapping("/authenticate")
public class LoginController {

	@Autowired
	private LoginService service;

	@PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> register(@RequestBody LoginRequest request) {

		service.signUp(request);
		return ResponseEntity.ok("Registration successful. Welcome.");

	}

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

		LoginResponse response = service.login(request);
		return ResponseEntity.ok(response);
	}
}
