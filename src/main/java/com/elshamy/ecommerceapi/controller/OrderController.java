package com.elshamy.ecommerceapi.controller;

import com.elshamy.ecommerceapi.dto.OrderResponseDTO;

import com.elshamy.ecommerceapi.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<OrderResponseDTO> checkout(){
        OrderResponseDTO orderResponseDTO = orderService.checkout();
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDTO);
    }

}
