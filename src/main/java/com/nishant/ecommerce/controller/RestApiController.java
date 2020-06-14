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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/user")
@Api(value = "user", description = "Add and fetch user", tags = "user")
public class RestApiController {

	@Autowired
	UserRepository repo;

	@ApiOperation(value = "Save User Register User", notes = "Register User Get All User in the sysytem", nickname = "SaveUser")
	@PostMapping(path = "/saveUser", consumes = { "application/json" })
	public User SaveUser(@RequestBody User user) {

		repo.save(user);
		return user;

	}

	@ApiOperation(value = "Get All Users", notes = "Get All User in the sysytem", nickname = "getUsers")
	@GetMapping("/getAll")
	public List<User> GetUserList() {

		return repo.findAll();

	}

	@ApiOperation(value = "Get user by id", notes = "Get User by Id in the sysytem", nickname = "getUser")
	@GetMapping("/getAll/{id}")
	public Optional<User> GetUser(@PathVariable("id") Long id) {

		return repo.findById(id);

	}
}
