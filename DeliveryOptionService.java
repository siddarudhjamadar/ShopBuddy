package com.example.email.service;

import java.util.List;

import com.example.email.domain.DeliveryOption;

public interface DeliveryOptionService {

    public List<DeliveryOption> getAllDeliveryOptions();
    public DeliveryOption addDeliveryOption(DeliveryOption deliveryOption);

}
