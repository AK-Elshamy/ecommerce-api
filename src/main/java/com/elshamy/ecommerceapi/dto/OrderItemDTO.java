package com.elshamy.ecommerceapi.dto;

import java.math.BigDecimal;

public record OrderItemDTO(
        String productName,
        int quantity,
        BigDecimal priceAtPurchase,
        BigDecimal subtotal
) {}
