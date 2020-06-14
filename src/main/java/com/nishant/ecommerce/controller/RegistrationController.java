package com.nishant.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nishant.ecommerce.dto.RequestDTO;
import com.nishant.ecommerce.dto.ResponseDTO;
import com.nishant.ecommerce.model.User;
import com.nishant.ecommerce.repository.UserRepository;
import com.nishant.ecommerce.service.IRegistration;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author nishant This is an <b>ecommerce application</b>through which we and
 *         register user on the plateform. Add product <b>order the product.</b>
 *
 */

@Controller
@Api(value = "registration", description = "Operations pertaining to user registration")
public class RegistrationController {

	@Autowired
	@Qualifier("registrationImpl")
	private IRegistration registration;

	@Autowired
	UserRepository repo;
	
	/**
	 * 
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 */

	@ApiOperation(value = "Register a user", notes = "Register User in the sysytem", nickname = "registration")
	@RequestMapping(path = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ResponseDTO> saveUserDetails(@RequestBody RequestDTO dto, HttpServletRequest request,
			HttpServletResponse response) {
		ResponseDTO result = new ResponseDTO();

		result = registration.saveDetails(dto);

		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Get All Users", notes = "Get All User in the sysytem", nickname = "getUsers")
	@PostMapping(path = "/GetAllList", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUserDetails(User user) {
		return repo.findAll();

	}

}
