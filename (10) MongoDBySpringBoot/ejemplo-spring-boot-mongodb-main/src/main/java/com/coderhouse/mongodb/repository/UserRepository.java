package com.coderhouse.mongodb.repository;

import com.coderhouse.mongodb.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<Product, String> {

    Product findByNombre(String nombre);
    List<Product> findByPrecioGreaterThan(Long precio);
}
