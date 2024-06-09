package com.example.email.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.email.domain.Order;
import com.example.email.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrdersControl {
	

	    @Autowired
	    private OrderService ordersService;

	    @PostMapping
	    public Order createOrder(@RequestBody Order order) {
	        return ordersService.createOrder(order);
	    }

}
