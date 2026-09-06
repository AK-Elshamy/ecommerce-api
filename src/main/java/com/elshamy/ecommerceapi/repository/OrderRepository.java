package com.elshamy.ecommerceapi.repository;

import com.elshamy.ecommerceapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
