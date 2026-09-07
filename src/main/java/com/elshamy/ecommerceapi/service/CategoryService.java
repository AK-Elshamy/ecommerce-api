package com.elshamy.ecommerceapi.service;

import com.elshamy.ecommerceapi.dto.CategoryDTO;
import com.elshamy.ecommerceapi.dto.CreateCategoryRequest;
import com.elshamy.ecommerceapi.entity.Category;
import com.elshamy.ecommerceapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }


    private CategoryDTO toDTO(Category category){
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription()
        );

    }

    public List<CategoryDTO> getAllCategories(){
        return categoryRepository.findAll().stream().
                map(this::toDTO).toList();
    }

    public CategoryDTO createCategory(CreateCategoryRequest request){
        Category category = new Category();
        category.setName(request.name());
        category.setDescription(request.description());
        Category saved = categoryRepository.save(category);
        return toDTO(saved);
    }
}


