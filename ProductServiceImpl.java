package com.example.email.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.email.constatnts.MiscConstatnts;
import com.example.email.domain.Product;
import com.example.email.dto.ProductDto;
import com.example.email.mapper.ProductMapper;
import com.example.email.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	
	@Autowired
	private MongoOperations mongoOperations;
	
	@Autowired
	private ProductMapper mapper;

	@Override
	public ProductDto save(ProductDto productDto) {
		Product product =null; 
		if(productDto!=null) {
			product = mapper.toDomain(productDto);
			product = mongoOperations.save(product);
		}
		return mapper.toDto(product);
	}

	@Override
	public ProductDto getById(String id) {
		Product product =null; 
		if(id!=null) {
			product = mongoOperations.findById(id, Product.class);
		}
		return mapper.toDto(product);
	}

	@Override
	public ProductDto update(ProductDto productDto) {
		Product product =null; 
		if(productDto!=null && productDto.getId()!=null) {
			product = mapper.toDomain(productDto);
			product = mongoOperations.save(product);
		}
		return mapper.toDto(product);
	}

	@Override
	public String deleteById(String id) {
		if(id!=null) {
			Criteria criteria = new Criteria();
			criteria.and("_id").is(id);
			Update update = new Update();
			update.set("recordStatus", MiscConstatnts.RECORD_STATUS);
			mongoOperations.updateFirst(new Query(criteria), update, Product.class);
			return MiscConstatnts.PRODUCT_SUCESSFULLY_DELETED;
		}else {
		return 	MiscConstatnts.PRODUCT_NOT_FOUND;
		}
	}

	@Override
	public boolean validateDuplicateProd(String productName) {
		Criteria criteria = new Criteria();
		criteria.and("productName").is(productName);
		Query query = new Query(criteria);
		return mongoOperations.exists(query, Product.class);
	}
}
