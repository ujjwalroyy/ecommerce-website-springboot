package com.springboot.ecom.service;

import com.springboot.ecom.exception.CartItemException;
import com.springboot.ecom.exception.UserException;
import com.springboot.ecom.model.Cart;
import com.springboot.ecom.model.CartItem;
import com.springboot.ecom.model.Product;

public interface CartItemService {
	
	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem)throws CartItemException, UserException;
	
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);
	
	public void removeCartItem(Long userId, Long cartItemId)throws CartItemException, UserException;
	
	public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
