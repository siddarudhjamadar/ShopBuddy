package com.example.email.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.email.domain.DeliveryOption;
import com.example.email.repository.DeliveryOptionsRepository;
import com.example.email.service.DeliveryOptionService;

@Service
public class DeliveryOptionServiceImpl implements DeliveryOptionService{

	    @Autowired
	    private DeliveryOptionsRepository deliveryOptionsRepository;

	    public List<DeliveryOption> getAllDeliveryOptions() {
	        return deliveryOptionsRepository.findAll();
	    }

	    public DeliveryOption addDeliveryOption(DeliveryOption deliveryOption) {
	        return deliveryOptionsRepository.save(deliveryOption);
	    }

}
