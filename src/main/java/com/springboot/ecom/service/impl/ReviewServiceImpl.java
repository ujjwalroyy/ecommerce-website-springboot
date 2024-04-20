package com.springboot.ecom.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.ecom.exception.ProductException;
import com.springboot.ecom.model.Product;
import com.springboot.ecom.model.Review;
import com.springboot.ecom.model.User;
import com.springboot.ecom.repository.ProductRepository;
import com.springboot.ecom.repository.ReviewRepository;
import com.springboot.ecom.request.ReviewRequest;
import com.springboot.ecom.service.ProductService;
import com.springboot.ecom.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{

	private ReviewRepository reviewRepository;
	private ProductService productService;
	private ProductRepository productRepository;
	
	public ReviewServiceImpl(ReviewRepository reviewRepository, ProductService productService,
			ProductRepository productRepository) {
		this.reviewRepository = reviewRepository;
		this.productService = productService;
		this.productRepository = productRepository;
	}

	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
		// TODO Auto-generated method stub
		Product product = productService.findProductById(req.getProductId());
		
		Review review = new Review();
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReview(Long productId) {
		// TODO Auto-generated method stub
		return reviewRepository.getAllProductsReview(productId);
	}

	
}
