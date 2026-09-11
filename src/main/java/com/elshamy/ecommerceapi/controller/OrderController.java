package com.elshamy.ecommerceapi.controller;


import com.elshamy.ecommerceapi.dto.OrderResponseDTO;
import com.elshamy.ecommerceapi.dto.UpdateOrderStatusRequest;
import com.elshamy.ecommerceapi.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getMyOrders(){
        return ResponseEntity.ok(orderService.getMyOrders());
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }


    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponseDTO> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody
            UpdateOrderStatusRequest request){
        return ResponseEntity.ok(orderService.updateOrderStatus(id, request));
    }
}
