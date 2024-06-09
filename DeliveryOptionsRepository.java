package com.example.email.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.email.domain.DeliveryOption;

@Repository
public interface DeliveryOptionsRepository extends MongoRepository<DeliveryOption, String>{

}
