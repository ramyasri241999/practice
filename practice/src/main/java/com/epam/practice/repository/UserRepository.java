package com.epam.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.practice.dto.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	
	
}
