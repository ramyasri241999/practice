package com.epam.practice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.practice.dto.User;
import com.epam.practice.model.UserResponse;
import com.epam.practice.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/getUser/{userId}")
	public ResponseEntity<UserResponse> getUser(@PathVariable Long userId){
		logger.info("UserController:: getUser request:: "+ userId);
		UserResponse user = userService.getUserById(userId);
		logger.info("UserController:: getUser response:: "+ user);
		return ResponseEntity.status(HttpStatus.OK).body(user);
		
	}
}
