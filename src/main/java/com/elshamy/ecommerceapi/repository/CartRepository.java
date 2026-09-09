package com.elshamy.ecommerceapi.repository;

import com.elshamy.ecommerceapi.entity.Cart;
import com.elshamy.ecommerceapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
