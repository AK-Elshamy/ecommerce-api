package com.elshamy.ecommerceapi.repository;

import com.elshamy.ecommerceapi.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
