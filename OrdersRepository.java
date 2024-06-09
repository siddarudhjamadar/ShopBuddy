package com.example.email.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.email.domain.Order;

@Repository
public interface OrdersRepository extends MongoRepository<Order, String>{

}
