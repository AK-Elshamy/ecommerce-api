package com.elshamy.ecommerceapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;

public record AddToCartRequest(
        @NotNull
        Long productId,
        @Min(value = 1, message = "Quantity must be at least 1")
        int quantity
) {}
