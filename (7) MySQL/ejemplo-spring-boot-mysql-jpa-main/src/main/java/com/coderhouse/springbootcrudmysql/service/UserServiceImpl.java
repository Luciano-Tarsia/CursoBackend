package com.coderhouse.springbootcrudmysql.service;

import com.coderhouse.springbootcrudmysql.model.Product;
import com.coderhouse.springbootcrudmysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public Product createProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Product updateProductById(Product product, Long id) {
        product.setId(id);
        return repository.save(product);
    }

    @Override
    public void delete(Product product, Long id) {
        product.setId(id);
        repository.delete(product);
    }

    @Override
    public Product findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Product> findAll() {
        List<Product> userList =  new ArrayList<>();
        repository.findAll().forEach(userList::add);
        return userList;
    }
}
