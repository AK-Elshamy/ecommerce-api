package com.elshamy.ecommerceapi.repository;

import com.elshamy.ecommerceapi.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
