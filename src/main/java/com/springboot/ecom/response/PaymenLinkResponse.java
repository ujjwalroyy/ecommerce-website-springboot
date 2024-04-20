package com.springboot.ecom.response;

public class PaymenLinkResponse {

	
	private String payment_link_url;
	private String paymnet_link_id;
	
	public PaymenLinkResponse() {
		//TODO Auto-genreted constructor stub
	}

	public PaymenLinkResponse(String payment_link_url, String paymnet_link_id) {
		super();
		this.payment_link_url = payment_link_url;
		this.paymnet_link_id = paymnet_link_id;
	}

	public String getPayment_link_url() {
		return payment_link_url;
	}

	public void setPayment_link_url(String payment_link_url) {
		this.payment_link_url = payment_link_url;
	}

	public String getPaymnet_link_id() {
		return paymnet_link_id;
	}

	public void setPaymnet_link_id(String paymnet_link_id) {
		this.paymnet_link_id = paymnet_link_id;
	}
}
