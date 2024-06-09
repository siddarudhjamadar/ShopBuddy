package com.example.email.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.email.domain.User;
import com.example.email.dto.UserDto;

@Component
public class UserMapper {
	
	public UserDto toDto(User user) {
		if(user==null) {
			return null;
		}
		UserDto dto = new  UserDto();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setAddress(user.getAddress());
		dto.setMobileNumber(user.getMobileNumber());
		dto.setRole(user.getRole());
		dto.setRecordStatus(null);
		return dto;
	}
	
	public User toDomain(UserDto userDto) {
		if(userDto==null) {
			return null;
		}
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		return user;
	}
}
