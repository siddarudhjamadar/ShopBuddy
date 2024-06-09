package com.example.email.serviceimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.email.constatnts.MiscConstatnts;
import com.example.email.domain.Cart;
import com.example.email.domain.Product;
import com.example.email.domain.User;
import com.example.email.dto.CartDto;
import com.example.email.dto.UserDto;
import com.example.email.mapper.CartMapper;
import com.example.email.mapper.UserMapper;
import com.example.email.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private  MongoOperations  mongoOperations;
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private CartMapper cartMapper;

	@Override
	public UserDto save(UserDto userDto) {
		User user = null;
		if(userDto == null) {
			return null;
		}
		 user = mapper.toDomain(userDto);
		 user  = mongoOperations.save(user);
		 return mapper.toDto(user);
	}

	@Override
	public UserDto getById(String id) {
		User user = null;
		if(id!=null) {
			user = mongoOperations.findById(id, User.class);
		}
		 return mapper.toDto(user);
	}

	@Override
	public UserDto update(UserDto userDto) {
		User user = null;
		if(userDto!=null && userDto.getId()!=null) {
			user = mapper.toDomain(userDto);
			user = mongoOperations.save(user);
		}
		 return mapper.toDto(user);
	}

	@Override
	public String deleteById(String id) {
		if(id!=null) {
			Criteria criteria = new Criteria();
			criteria.and("_id").is(id);
			Update update = new Update();
			update.set("recordStatus", MiscConstatnts.RECORD_STATUS);
			mongoOperations.updateFirst(new Query(criteria), update, User.class);
			return MiscConstatnts.USER_SUCESSFULLY_DELETED;
		}else {
		return 	MiscConstatnts.USER_NOT_FOUND;
		}
	}

	@Override
	public CartDto addToCart(CartDto cartDto) {
		Cart cart = cartMapper.toDomain(cartDto);
		if(cart!=null) {
			mongoOperations.save(cart);
		}
		return null;
	}

	@Override
	public CartDto getCart(String id) {
	    Cart cart = mongoOperations.findById(id, Cart.class);
	    if (cart == null || cart.getId() == null) {
	        return null;
	    }

	    double totalPrice = calculateTotalPrice(cart);
	    CartDto cartDto = convertToDto(cart, totalPrice);

	    if (cart.getProductName() != null) {
	        updateProductQuantity(cart.getProductName(), cart.getQuantity());
	    }

	    return cartDto;
	}

	private double calculateTotalPrice(Cart cart) {
	    return cart.getQuantity() * cart.getPrice();
	}

	private CartDto convertToDto(Cart cart, double totalPrice) {
	    CartDto cartDto = new CartDto();
	    BeanUtils.copyProperties(cart, cartDto);
	    cartDto.setTotalPrice(totalPrice);
	    return cartDto;
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
