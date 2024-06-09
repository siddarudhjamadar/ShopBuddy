package com.example.email.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.email.domain.Product;
import com.example.email.dto.ProductDto;

@Component
public class ProductMapper {
	
	public Product toDomain(ProductDto productDto) {
		if(productDto==null) {
			return null;
		}
		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);
		return product;
	}
	
	public ProductDto toDto(Product product) {
		if(product==null) {
			return null;	
		}
		ProductDto productDto = new ProductDto();
		BeanUtils.copyProperties(product, productDto);
		return productDto;
	}

}
