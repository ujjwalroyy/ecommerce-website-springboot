package com.springboot.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecom.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByEmail(String email);
}
