package com.elshamy.ecommerceapi.service;

import com.elshamy.ecommerceapi.dto.CategoryDTO;
import com.elshamy.ecommerceapi.dto.CreateCategoryRequest;
import com.elshamy.ecommerceapi.entity.Category;
import com.elshamy.ecommerceapi.exception.ResourceNotFoundException;
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

    public CategoryDTO updateCategory(CreateCategoryRequest request, Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        category.setDescription(request.description());
        category.setName(request.name());
        return toDTO(categoryRepository.save(category));
    }
    public void deleteCategory(Long id){
        if(! categoryRepository.existsById(id)){
            throw new ResourceNotFoundException("Category not found");
        }
        categoryRepository.deleteById(id);
    }
}


