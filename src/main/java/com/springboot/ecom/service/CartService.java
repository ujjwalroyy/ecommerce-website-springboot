package com.springboot.ecom.service;

import com.springboot.ecom.exception.ProductException;
import com.springboot.ecom.model.Cart;
import com.springboot.ecom.model.User;
import com.springboot.ecom.request.AddItemRequest;

public interface CartService {

	public Cart createCart(User user);
	
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);
}
