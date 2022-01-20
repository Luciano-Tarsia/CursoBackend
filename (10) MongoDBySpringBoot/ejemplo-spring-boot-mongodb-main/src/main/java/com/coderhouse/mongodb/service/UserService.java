package com.coderhouse.mongodb.service;

import com.coderhouse.mongodb.model.Product;

import java.util.List;

public interface UserService {

    Product createProduct(Product product);
    Product findByNombre(String nombre);
    List<Product> findAll();
    List<Product> findByPrecioGreaterThan(Long precio);
    List<Product> findAllByCategoriaSortedLimit(String categoria, String orderBy, int limit);
}
