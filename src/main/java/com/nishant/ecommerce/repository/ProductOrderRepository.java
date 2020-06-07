package com.nishant.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nishant.ecommerce.model.ProductOrder;
import com.nishant.ecommerce.model.ProductOrderPk;

@Repository
public interface ProductOrderRepository extends CrudRepository<ProductOrder, ProductOrderPk> {

}
