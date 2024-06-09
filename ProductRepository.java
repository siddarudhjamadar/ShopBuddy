package com.example.email.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.email.domain.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>{

}
