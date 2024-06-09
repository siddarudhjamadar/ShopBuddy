package com.example.email.serviceimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.email.domain.DeliveryOption;
import com.example.email.domain.Order;
import com.example.email.domain.Product;
import com.example.email.repository.DeliveryOptionsRepository;
import com.example.email.repository.OrdersRepository;
import com.example.email.service.OrderService;
import com.example.email.service.UserService;

@Service
public class orderServiceImpl implements OrderService{
	    @Autowired
	    private OrdersRepository ordersRepository;

	    @Autowired
	    private DeliveryOptionsRepository deliveryOptionsRepository;
	    
	    @Autowired
	    private MongoOperations mongoOperations;
	    
	    @Autowired
	    private UserService userService;

	    public Order createOrder(Order order) {
	        DeliveryOption deliveryOption = deliveryOptionsRepository.findById(order.getDeliveryOptionId()).orElseThrow(() -> new RuntimeException("Invalid delivery option"));
	        order.setTotalPrice(calculateTotalPrice(order, deliveryOption));
	        order.setDeliveryDate(calculateDeliveryDate(order, deliveryOption));
	        for (Product product : order.getProducts()) {
	        	updateProductQuantity(product.getProductName(),product.getAvailableQty());
			}
	        return ordersRepository.save(order);
	    }

	    private double calculateTotalPrice(Order order, DeliveryOption deliveryOption) {
	        return order.getProducts().stream().mapToDouble(p -> p.getPrice()* p.getSelectedQty()).sum() + deliveryOption.getPrice();
	    }

	    private Date calculateDeliveryDate(Order order, DeliveryOption deliveryOption) {
	        return new Date(); 
	    }
	    
	    private void updateProductQuantity(String productName, double qty) {
		    Criteria cr = new Criteria();
		    cr.and("productName").is(productName);
		    Product product = mongoOperations.findOne(new Query(cr), Product.class);

		    if (product != null && product.getAvailableQty() != 0) {
		        double remainingQty = product.getAvailableQty() - qty;
		        product.setAvailableQty(remainingQty);
		        mongoOperations.save(product);
		    }
		}
}
