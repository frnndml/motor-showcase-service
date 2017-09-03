package com.motorshowcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motorshowcase.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
}
