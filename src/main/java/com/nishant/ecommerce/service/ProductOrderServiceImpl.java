package com.nishant.ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nishant.ecommerce.model.ProductOrder;
import com.nishant.ecommerce.repository.ProductOrderRepository;

@Service
@Transactional
public class ProductOrderServiceImpl implements ProductOrderService {

	private ProductOrderRepository productOrderRepository;

	public ProductOrderServiceImpl(ProductOrderRepository productOrderRepository) {
		this.productOrderRepository = productOrderRepository;
	}

	@Override
	public ProductOrder create(ProductOrder productOrder) {
		return this.productOrderRepository.save(productOrder);
	}

}
