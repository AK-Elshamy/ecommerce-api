package com.elshamy.ecommerceapi.dto;

import java.math.BigDecimal;
import java.util.List;

public record CartResponseDTO(
        List<CartItemDTO> items,
        BigDecimal totalAmount
) {}
