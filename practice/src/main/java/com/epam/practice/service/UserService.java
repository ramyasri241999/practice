package com.epam.practice.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.epam.practice.customException.NoSuchUserFoundException;
import com.epam.practice.dto.User;
import com.epam.practice.model.UserResponse;
import com.epam.practice.repository.UserRepository;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	private final UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public UserResponse getUserById(Long userId) {
		logger.info("UserService:: getUserById request:: "+ userId);	
		User user = userRepo.findById(userId).orElseThrow(()-> new NoSuchUserFoundException("No Such User Found"));
		//logger.info("UserService:: getUserById response:: "+ user);	
		
		return mapToResponse(user);
	}
	
	private UserResponse mapToResponse(User user) {

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setCreatedAt(user.getCreatedAt());
//
//        UserProfile profile = user.getUserProfile();   // if we need to include this as well we need to make UserProfile class public
//
//        if (profile != null) {
//            response.setAddress(profile.getAddress());
//            response.setPhone(profile.getPhone());
//            response.setDob(profile.getDob());
//        }
        logger.info("Inside the mapToResponse "+ response);

        return response;
    }
}
