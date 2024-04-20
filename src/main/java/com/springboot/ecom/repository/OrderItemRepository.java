package com.springboot.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecom.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
