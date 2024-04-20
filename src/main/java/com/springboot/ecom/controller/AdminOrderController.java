package com.springboot.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecom.exception.OrderException;
import com.springboot.ecom.model.Order;
import com.springboot.ecom.response.ApiResponse;
import com.springboot.ecom.service.OrderService;

@RestController
@CrossOrigin(origins = "https://localhost:3000")
@RequestMapping("api/admin/orders")
public class AdminOrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/")
	public ResponseEntity<List<Order>> getAllOrderHandler(){
		List<Order> orders = orderService.getAllOrders();
		return new ResponseEntity<List<Order>>(orders, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{orderId}/confirmed")
	public ResponseEntity<Order> ConfirmedOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization")String jwt) throws OrderException{
	
		Order order = orderService.shippedOrder(orderId);
		
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	@PutMapping("/{orderId}/ship")
	public ResponseEntity<Order> ShippingOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization")String jwt)throws OrderException{
		Order order = orderService.shippedOrder(orderId);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	@PutMapping("/{orderId}/deliver")
	public ResponseEntity<Order> DeliverOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization")String jwt)throws OrderException{
		Order order = orderService.deliveredOrder(orderId);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	@PutMapping("/{orderId}/cancel")
	public ResponseEntity<Order> CancelOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization")String jwt) throws OrderException{
		Order order = orderService.cancledOrder(orderId);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	@DeleteMapping("/{orderId}/delete")
	public ResponseEntity<ApiResponse> DeleteOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization")String jwt) throws OrderException{
		orderService.deleteOrder(orderId);
		
		ApiResponse res = new ApiResponse();
		res.setMessage("order deleted successfully");
		res.setStatus(true);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
