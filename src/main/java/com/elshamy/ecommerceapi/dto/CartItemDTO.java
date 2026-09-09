package com.elshamy.ecommerceapi.dto;

import java.math.BigDecimal;

public record CartItemDTO(
        Long productId,
        String productName,
        BigDecimal price,
        int quantity,
        BigDecimal subtotal // price * quantity
) {}