package com.elshamy.ecommerceapi.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(
        @NotBlank(message = "Category Name is required")
        String name,
        String description

) {}
