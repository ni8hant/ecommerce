package com.nishant.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.nishant.ecommerce.model.Product;

public interface ProductRepository extends CrudRepository<Product,Long> {

}
