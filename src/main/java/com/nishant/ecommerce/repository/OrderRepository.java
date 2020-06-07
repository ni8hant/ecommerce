package com.nishant.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nishant.ecommerce.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

}
