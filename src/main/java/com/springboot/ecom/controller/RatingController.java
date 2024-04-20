package com.springboot.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecom.exception.ProductException;
import com.springboot.ecom.exception.UserException;
import com.springboot.ecom.model.Rating;
import com.springboot.ecom.model.User;
import com.springboot.ecom.request.RatingRequest;
import com.springboot.ecom.service.RatingService;
import com.springboot.ecom.service.UserService;

@RestController
@CrossOrigin(origins = "https://localhost:3000")
@RequestMapping("/api/rating")
public class RatingController {

	@Autowired
	private UserService userService;
	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/create")
	public ResponseEntity<Rating> createRating(@RequestBody RatingRequest req, @RequestHeader("Authorization")String jwt) throws UserException, ProductException{
		User user = userService.findUserProfileByJwt(jwt);
		Rating rating = ratingService.createRating(req, user);
		return new ResponseEntity<Rating>(rating, HttpStatus.CREATED);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Rating>> getProductRating(@PathVariable Long productId, @RequestHeader("Authorization")String jwt)throws UserException, ProductException{
		User user = userService.findUserProfileByJwt(jwt);
		List<Rating> rating = ratingService.getProductRating(productId);
		return new ResponseEntity<List<Rating>>(rating, HttpStatus.CREATED);
	}
}
