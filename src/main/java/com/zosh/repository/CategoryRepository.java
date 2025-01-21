package com.zosh.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.zosh.modal.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

   Category findByCategoryId(String categoryId);
}
