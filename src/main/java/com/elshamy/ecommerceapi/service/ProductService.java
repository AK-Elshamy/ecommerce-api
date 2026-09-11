package com.elshamy.ecommerceapi.service;

import com.elshamy.ecommerceapi.dto.CreateProductRequest;
import com.elshamy.ecommerceapi.dto.ProductDTO;
import com.elshamy.ecommerceapi.entity.Category;
import com.elshamy.ecommerceapi.entity.Product;
import com.elshamy.ecommerceapi.exception.ResourceNotFoundException;
import com.elshamy.ecommerceapi.repository.CategoryRepository;
import com.elshamy.ecommerceapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(
            ProductRepository productRepository,
            CategoryRepository categoryRepository) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductDTO> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ProductDTO createProduct(CreateProductRequest request) {

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found wit id: " + request.categoryId()));

        Product product = new Product();

        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setStockQuantity(request.stockQuantity());
        product.setCategory(category);

        Product saved = productRepository.save(product);

        return toDTO(saved);
    }

    private ProductDTO toDTO(Product product) {

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCategory() != null
                        ? product.getCategory().getName()
                        : null
        );
    }

    public ProductDTO updateProduct(Long id, CreateProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));


        product.setCategory(category);
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setStockQuantity(request.stockQuantity());



        return toDTO(productRepository.save(product));
    }

    public void deleteProduct(Long id){
        if(! productRepository.existsById(id)){
            throw new ResourceNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }

}