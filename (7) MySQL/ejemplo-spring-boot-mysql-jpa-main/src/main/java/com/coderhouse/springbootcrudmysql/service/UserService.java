package com.coderhouse.springbootcrudmysql.service;

import com.coderhouse.springbootcrudmysql.model.Product;

import java.util.List;

public interface UserService {

    Product createProduct(Product product);
    Product getProductById(Long id);
    Product updateProductById(Product product, Long id);
    void delete(Product product, Long id);
    Product findByName(String name);
    List<Product> findAll();

}
