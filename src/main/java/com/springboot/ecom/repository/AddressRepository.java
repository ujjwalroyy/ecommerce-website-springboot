package com.springboot.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecom.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
