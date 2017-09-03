package com.motorshowcase.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.motorshowcase.model.User;
import com.motorshowcase.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository repository;
	private BCryptPasswordEncoder encoder;
	
	@Override
	public User findByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public User save(User user) {
		encodePassword(user);
		return repository.save(user);
	}

	private void encodePassword(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
	}
}
