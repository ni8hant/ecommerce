package com.nishant.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.nishant.ecommerce.model.ProductOrder;
import com.nishant.ecommerce.model.ProductOrderPk;

public interface ProductOrderRepository extends CrudRepository<ProductOrder, ProductOrderPk> {

}
