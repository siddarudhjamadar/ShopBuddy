package com.example.email.service;


import com.example.email.dto.CartDto;
import com.example.email.dto.UserDto;

public interface UserService {
	public UserDto save(UserDto userDto);
	public UserDto getById(String id);
	public UserDto update(UserDto userDto);
	public String deleteById(String id);
	public CartDto addToCart(CartDto cartDto);
	public CartDto getCart(String id);

}
