package com.springboot.ecom.service;

import com.springboot.ecom.exception.UserException;
import com.springboot.ecom.model.User;

public interface UserService {
	public User findUserById(Long userId)throws UserException;
	
	public User findUserProfileByJwt(String jwt)throws UserException;
	
	
}
