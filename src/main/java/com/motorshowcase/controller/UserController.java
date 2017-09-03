package com.motorshowcase.controller;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.ok;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.motorshowcase.model.User;
import com.motorshowcase.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

	private UserService service;
	
	@GetMapping("{username:.+}")
	public ResponseEntity<?> findByUsername(@PathVariable("username") String username) {
		User user = service.findByUsername(username);
		if(user == null) {
			String message = String.format("User with username %s not found", username);
			return new ResponseEntity<String>(message, NOT_FOUND);
		}
		return ok(user);
	}
	
	@PostMapping("sign-up")
	public ResponseEntity<User> save(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		User saved = service.save(user);
		return ResponseEntity.created(ucBuilder.path("/users/email/{email}").buildAndExpand(saved.getUsername()).toUri()).build();
	}
}
