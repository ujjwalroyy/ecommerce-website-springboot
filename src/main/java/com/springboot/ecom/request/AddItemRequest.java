package com.springboot.ecom.request;

public class AddItemRequest {

	private Long productId;
	private String size;
	private int quantity;
	private Integer price;
	public AddItemRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AddItemRequest(Long productId, String size, int quantity, Integer price) {
		super();
		this.productId = productId;
		this.size = size;
		this.quantity = quantity;
		this.price = price;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
}
