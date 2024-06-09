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

import com.example.email.dto.CartDto;
import com.example.email.dto.UserDto;
import com.example.email.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserResource {

	@Autowired
	private UserService userService;
	
    private static final Logger log = LoggerFactory.getLogger(ProductResource.class);

	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody UserDto dto){
		log.info("Inside save()");
		try {
			return new ResponseEntity<>(userService.save(dto),HttpStatus.OK);
		}catch (Exception e) {
			log.error("Error while calling save()" ,e);
			e.printStackTrace();
			return new ResponseEntity<>(userService.save(dto),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable String id){
		log.info("Inside getUserById()");
		try {
			return new ResponseEntity<>(userService.getById(id),HttpStatus.OK);
		}catch (Exception e) {
			log.error("Error while calling getUserById()" ,e);
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody UserDto dto){
		log.info("Inside updateUser()");
		try {
			return new ResponseEntity<>(userService.update(dto),HttpStatus.OK);
		}catch (Exception e) {
			log.error("Error while calling updateUser()" ,e);
			e.printStackTrace();
			return new ResponseEntity<>(userService.save(dto),HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable String id){
		log.info("Inside deleteUserById()");
		try {
			return new ResponseEntity<>(userService.deleteById(id),HttpStatus.OK);
		}catch (Exception e) {
			log.error("Error while calling deleteUserById()" ,e);
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/cart/{id}")
	public ResponseEntity<?> getCart(@PathVariable String id){
		log.info("Inside getCart()");
		try {
			return new ResponseEntity<>(userService.getById(id),HttpStatus.OK);
		}catch (Exception e) {
			log.error("Error while calling getCart()" ,e);
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@PostMapping("/cart")
	public ResponseEntity<?> saveCart(@RequestBody CartDto dto){
		log.info("Inside saveCart()");
		try {
			return new ResponseEntity<>(userService.addToCart(dto),HttpStatus.OK);
		}catch (Exception e) {
			log.error("Error while calling saveCart()" ,e);
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
