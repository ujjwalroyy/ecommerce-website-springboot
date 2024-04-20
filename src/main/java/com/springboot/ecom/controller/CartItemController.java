package com.springboot.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecom.exception.CartItemException;
import com.springboot.ecom.exception.UserException;
import com.springboot.ecom.model.CartItem;
import com.springboot.ecom.model.User;
import com.springboot.ecom.response.ApiResponse;
import com.springboot.ecom.service.CartItemService;
import com.springboot.ecom.service.UserService;

@RestController
@CrossOrigin(origins = "https://localhost:3000")
@RequestMapping("/api/cartItem")
public class CartItemController {
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private UserService userService;
	
	@DeleteMapping("/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId,
			@RequestHeader("Authorization")String jwt)throws UserException, CartItemException{
		User user = userService.findUserProfileByJwt(jwt);
		cartItemService.removeCartItem(user.getId(), cartItemId);
		
		ApiResponse res = new ApiResponse();
		res.setMessage("Delete Item from cart");
		res.setStatus(true);
		return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
	}
	@PutMapping("/{cartItemId}")
	public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItem cartItem,
			@PathVariable Long cartItemId,
			@RequestHeader("Authorization")String jwt)throws UserException, CartItemException{
		User user = userService.findUserProfileByJwt(jwt);
		CartItem updatedCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
		return new ResponseEntity<CartItem>(updatedCartItem, HttpStatus.OK);
	}
}
