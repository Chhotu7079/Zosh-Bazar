package com.zosh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zosh.modal.Product;

public interface ProductRepository extends JpaRepository<Product, Long>,
            JpaSpecificationExecutor<Product>{

    List<Product> findBySellerId(Long id);

    // @Query("SELECT p FROM Product p where (:query is null or lower(p.title)" + 
    //         "Like lower(concat('%', :query, '%')))" + 
    //         "or (:query is null or lower(p.category.name)))"+
    //         "like lower(concat('%', :query, '%')))")

    @Query("SELECT p FROM Product p " +
       "WHERE (:query IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', :query, '%'))) " +
       "OR (:query IS NULL OR LOWER(p.category.name) LIKE LOWER(CONCAT('%', :query, '%')))")

    List<Product> searchProducts(@Param("query") String query);
}
