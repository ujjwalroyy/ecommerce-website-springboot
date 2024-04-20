package com.springboot.ecom.response;

public class ApiResponse {
	
	private String jwt;
	private String message;
	private boolean status;
	
	
	public ApiResponse() {
		// TODO Auto-generated constructor stub
	}



	public ApiResponse(String jwt, String message, boolean status) {
		this.jwt = jwt;
		this.message = message;
		this.status = status;
	}



	public String getJwt() {
		return jwt;
	}



	public void setJwt(String jwt) {
		this.jwt = jwt;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public boolean isStatus() {
		return status;
	}



	public void setStatus(boolean status) {
		this.status = status;
	}

	
	
	
}
