package com.example.email.resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.email.domain.DeliveryOption;
import com.example.email.service.DeliveryOptionService;

import java.util.List;

@RestController
@RequestMapping("/delivery-options")
public class DeliveryOptionsController {

	    @Autowired
	    private DeliveryOptionService deliveryOptionsService;

	    @GetMapping
	    public List<DeliveryOption> getAllDeliveryOptions() {
	        return deliveryOptionsService.getAllDeliveryOptions();
	    }

	    @PostMapping
	    public DeliveryOption addDeliveryOption(@RequestBody DeliveryOption deliveryOption) {
	        return deliveryOptionsService.addDeliveryOption(deliveryOption);
	    }

}
