package com.springboot.ecom.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.ecom.exception.ProductException;
import com.springboot.ecom.model.Product;
import com.springboot.ecom.model.Rating;
import com.springboot.ecom.model.User;
import com.springboot.ecom.repository.RatingRepository;
import com.springboot.ecom.request.RatingRequest;
import com.springboot.ecom.service.ProductService;
import com.springboot.ecom.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService{

	private RatingRepository ratingRepository;
	private ProductService productService;
	
	public RatingServiceImpl(RatingRepository ratingRepository, ProductService productService) {
		this.ratingRepository = ratingRepository;
		this.productService = productService;
	}

	@Override
	public Rating createRating(RatingRequest req, User user) throws ProductException {
		// TODO Auto-generated method stub
		Product product = productService.findProductById(req.getProductId());
		
		Rating rating = new Rating();
		rating.setProduct(product);
		rating.setUser(user);
		rating.setRating(req.getRating());
		rating.setCreatedAt(LocalDateTime.now());
		
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getProductRating(Long productId) {
		// TODO Auto-generated method stub
		return ratingRepository.getAllProductsRating(productId);
	}
	
	
}
