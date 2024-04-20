package com.springboot.ecom.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecom.config.JwtProvider;
import com.springboot.ecom.exception.UserException;
import com.springboot.ecom.model.Cart;
import com.springboot.ecom.model.User;
import com.springboot.ecom.repository.UserRepository;
import com.springboot.ecom.request.LoginRequest;
import com.springboot.ecom.response.ApiResponse;
import com.springboot.ecom.service.CartService;
import com.springboot.ecom.service.impl.CustomeUserServiceImplementation;

@RestController
@CrossOrigin(origins = "https://localhost:3000")
@RequestMapping("/auth")
public class AuthController {
	
	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	private PasswordEncoder passwordEncoder;
	private CustomeUserServiceImplementation userServiceImpl;
	private CartService cartService;
	
	public AuthController(UserRepository userRepository, CustomeUserServiceImplementation userServiceImpl, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, CartService cartService) {
		// TODO Auto-generated constructor stub
		this.userRepository = userRepository;
		this.userServiceImpl = userServiceImpl;
		this.passwordEncoder = passwordEncoder;
		this.jwtProvider = jwtProvider;
		this.cartService = cartService;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<ApiResponse> createUserHandler(@RequestBody User user)throws UserException{
		String email = user.getEmail();
		String password = user.getPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		
		User isEmailExist = userRepository.findByEmail(email);
		
		if(isEmailExist != null) {
			throw new UserException("Email is already exist with another Account");
		}
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFirstName(firstName);
		createdUser.setLastName(lastName);
		
		User savedUser = userRepository.save(createdUser);
		Cart cart = cartService.createCart(savedUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		
		ApiResponse authResponse = new ApiResponse();
		authResponse.setJwt(token);
		authResponse.setStatus(true);
		authResponse.setMessage("Signup success");
		
		return new ResponseEntity<ApiResponse>(authResponse,HttpStatus.CREATED);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<ApiResponse> loginUserHandler(@RequestBody LoginRequest loginRequest){
		
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		Authentication authentication = authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		ApiResponse authResponse = new ApiResponse();
		authResponse.setJwt(token);
		authResponse.setStatus(true);
		authResponse.setMessage("Signin success");		
		return new ResponseEntity<ApiResponse>(authResponse,HttpStatus.CREATED);
	}

	
	private Authentication authenticate(String username, String password) {
		// TODO Auto-generated method stub
		UserDetails userDetails = userServiceImpl.loadUserByUsername(username);
		if(userDetails == null) {
			throw new BadCredentialsException("Invalid Username");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password...");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
}
