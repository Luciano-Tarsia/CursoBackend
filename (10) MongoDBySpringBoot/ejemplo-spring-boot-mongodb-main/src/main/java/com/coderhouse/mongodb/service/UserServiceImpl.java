package com.coderhouse.mongodb.service;

import com.coderhouse.mongodb.model.Product;
import com.coderhouse.mongodb.repository.UserRepository;
import com.coderhouse.mongodb.repository.UserTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserTemplateRepository template;

    @Override
    public Product createProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Product findByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Product> findByPrecioGreaterThan(Long precio) {
        return repository.findByPrecioGreaterThan(precio);
    }

    @Override
    public List<Product> findAllByCategoriaSortedLimit(String categoria, String orderBy, int limit) {
        return template.findAllByCategoriaSortedLimit(categoria, orderBy, limit);
    }

}