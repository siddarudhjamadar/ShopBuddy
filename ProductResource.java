package com.example.email.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.email.dto.ProductDto;
import com.example.email.service.ProductService;


@RestController
@RequestMapping("/api/product")
public class ProductResource {
	
	@Autowired
	private ProductService productService;
	
    private static final Logger log = LoggerFactory.getLogger(ProductResource.class);

	
	@PostMapping
	public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto){
		log.info("Inside createProduct()");
		try {
			 boolean isProductExist = productService.validateDuplicateProd(productDto.getProductName());
			 if(isProductExist) {
					return new ResponseEntity<>("Produc already exists!",HttpStatus.CONFLICT);	 
			 }
			return new ResponseEntity<>(productService.save(productDto),HttpStatus.OK);	
		}catch (Exception e) {
			log.error("Error while calling createProduct()",e);
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable String id){
		log.info("Inside getProductById()");
		try {
			return new ResponseEntity<>(productService.getById(id),HttpStatus.OK);	
		}catch (Exception e) {
			log.error("Error while calling getProductById()",e);
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		}	
	}
	
	@PutMapping
	public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto){
		log.info("Inside updateProduct()");
		try {
			return new ResponseEntity<>(productService.update(productDto),HttpStatus.OK);	
		}catch (Exception e) {
			log.error("Error while calling getProductById()" ,e);
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		}	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProductById(@PathVariable String id){
		log.info("Inside deleteProductById()");
		try {
			return new ResponseEntity<>(productService.deleteById(id),HttpStatus.OK);	
		}catch (Exception e) {
			log.error("Error while calling deleteProductById()" ,e);
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		}	
	}

}
