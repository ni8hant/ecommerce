package com.nishant.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nishant.ecommerce.model.User;
import com.nishant.ecommerce.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class RestApiController {

	@Autowired
	UserRepository repo;

	@PostMapping(path = "/saveUser", consumes = { "application/json" })
	public User SaveUser(@RequestBody User user) {

		repo.save(user);
		return user;

	}

	@GetMapping("/getAll")
	public List<User> GetUserList() {

		return repo.findAll();

	}

	@GetMapping("/getAll/{id}")
	public Optional<User> GetUser(@PathVariable("id") Long id) {

		return repo.findById(id);

	}
}
