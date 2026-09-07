package com.elshamy.ecommerceapi.controller;


import com.elshamy.ecommerceapi.dto.CategoryDTO;

import com.elshamy.ecommerceapi.dto.CreateCategoryRequest;
import com.elshamy.ecommerceapi.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest){
        CategoryDTO created = categoryService.createCategory(createCategoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


}
