package com.elshamy.ecommerceapi.dto;

import com.elshamy.ecommerceapi.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateOrderStatusRequest(
        @NotNull OrderStatus status
) {}