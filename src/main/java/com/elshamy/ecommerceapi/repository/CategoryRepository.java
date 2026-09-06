package com.elshamy.ecommerceapi.repository;

import com.elshamy.ecommerceapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
