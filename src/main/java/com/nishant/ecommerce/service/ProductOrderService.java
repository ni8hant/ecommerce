package com.nishant.ecommerce.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.nishant.ecommerce.model.ProductOrder;

@Validated
public interface ProductOrderService {

	ProductOrder create(@NotNull(message = "The products for order cannot be null.") @Valid ProductOrder orderProduct);

}
