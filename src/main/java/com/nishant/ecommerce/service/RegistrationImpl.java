package com.nishant.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nishant.ecommerce.dto.RequestDTO;
import com.nishant.ecommerce.dto.ResponseDTO;
import com.nishant.ecommerce.model.User;
import com.nishant.ecommerce.repository.UserRepository;



@Component("registrationImpl")
public class RegistrationImpl implements IRegistration {
	
	@Autowired
	UserRepository repo;
	
	@Override
	public ResponseDTO saveDetails(RequestDTO dto) {
		ResponseDTO resp = new ResponseDTO();
		try {
			User user = new User();
			user.setfName(dto.getfName());
			user.setlName(dto.getlName());
			repo.save(user);

			resp.setStatus("Success");
			resp.setCode("S00");
			resp.setMessage("Registration Successful");
		} catch (Exception e) {
			resp.setMessage(e.getMessage());
			resp.setStatus("Failed");
			resp.setCode("F00");
		}
		return resp;
	}

}
