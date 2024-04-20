package com.springboot.ecom.service;

import java.util.List;

import com.springboot.ecom.exception.ProductException;
import com.springboot.ecom.model.Rating;
import com.springboot.ecom.model.User;
import com.springboot.ecom.request.RatingRequest;

public interface RatingService {
	
	public Rating createRating(RatingRequest req, User user) throws ProductException;
	
	public List<Rating> getProductRating(Long productId);
	
	
}
