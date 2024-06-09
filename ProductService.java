package com.example.email.service;


import com.example.email.dto.ProductDto;


public interface ProductService {
	public ProductDto save(ProductDto productDto);
	public ProductDto getById(String id);
	public ProductDto update(ProductDto productDto);
	public String deleteById(String id);
	public boolean validateDuplicateProd(String productName);

}
