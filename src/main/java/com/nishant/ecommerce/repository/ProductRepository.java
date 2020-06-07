package com.nishant.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nishant.ecommerce.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

}
