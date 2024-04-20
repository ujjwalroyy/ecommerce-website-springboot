package com.springboot.ecom.service;

import java.util.List;

import com.springboot.ecom.exception.ProductException;
import com.springboot.ecom.model.Review;
import com.springboot.ecom.model.User;
import com.springboot.ecom.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req, User user)throws ProductException;
	
	public List<Review> getAllReview(Long productId);
}
