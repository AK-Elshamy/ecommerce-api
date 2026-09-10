package com.elshamy.ecommerceapi.repository;

import com.elshamy.ecommerceapi.entity.Order;
import com.elshamy.ecommerceapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
