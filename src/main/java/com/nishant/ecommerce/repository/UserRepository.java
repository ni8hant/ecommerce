package com.nishant.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nishant.ecommerce.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
