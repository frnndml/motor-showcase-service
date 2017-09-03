package com.motorshowcase.service;

import com.motorshowcase.model.User;

public interface UserService {

	User findByUsername(String username);

	User save(User user);
}
