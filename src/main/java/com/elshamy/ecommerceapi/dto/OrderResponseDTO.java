package com.elshamy.ecommerceapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(
        Long orderId,
        LocalDateTime orderDate,
        String status,
        List<OrderItemDTO> items,
        BigDecimal totalAmount
) {}