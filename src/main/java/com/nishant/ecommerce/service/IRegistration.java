package com.nishant.ecommerce.service;

import com.nishant.ecommerce.dto.RequestDTO;
import com.nishant.ecommerce.dto.ResponseDTO;

public interface IRegistration {

	ResponseDTO saveDetails(RequestDTO dto);

}
