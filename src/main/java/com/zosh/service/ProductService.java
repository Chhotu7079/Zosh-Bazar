package com.zosh.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.zosh.exception.ProductException;
import com.zosh.modal.Product;
import com.zosh.modal.Seller;
import com.zosh.request.CreateProductRequest;

public interface ProductService {

    public Product createProduct(CreateProductRequest req, Seller seller);
    public void deleteProduct(Long productId) throws ProductException;
    public Product updateProduct(Long productId, Product product) throws ProductException;
    Product findProductById(Long productId) throws ProductException;
    List<Product> searchProducts(String query);
    public Page<Product> getAllProducts(String category, 
             String brand, 
             String colors,
             String sizes,
             Integer minPrice,
             Integer maxPrice,
             Integer minDiscount,
             String sort,
             String stock,
             Integer pageNumber
    );
    // public Page<Product> getAllProducts(String category, String brand, String color, String size, Integer pageNumber2,
    //                         Integer pageNumber3, Integer minDiscount, String sort, String stock, Integer pageNumber);
    List<Product> getProductsBySellerId(Long sellerId);

    
}
