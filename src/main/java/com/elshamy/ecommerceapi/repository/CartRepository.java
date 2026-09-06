package com.elshamy.ecommerceapi.repository;

import com.elshamy.ecommerceapi.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
