package com.nishant.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.nishant.ecommerce.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
