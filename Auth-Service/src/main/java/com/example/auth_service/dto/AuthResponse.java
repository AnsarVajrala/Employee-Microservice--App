package com.example.auth_service.dto;

public class AuthResponse {
	private String token;

	public AuthResponse(String token) {
		super();
		this.token = token;
	}

	public AuthResponse() {
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
}
