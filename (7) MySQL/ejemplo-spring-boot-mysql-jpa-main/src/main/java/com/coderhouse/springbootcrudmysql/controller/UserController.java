package com.coderhouse.springbootcrudmysql.controller;

import com.coderhouse.springbootcrudmysql.model.Product;
import com.coderhouse.springbootcrudmysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coder-house")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/user")
    public Product createProduct(@RequestBody Product product) {
        return service.createProduct(product);
    }

    @GetMapping("/user/all")
    public List<Product> findProducts() {
        return service.findAll();
    }

    @GetMapping("/user")
    public Product findByName(@RequestParam String name) {
        return service.findByName(name);
    }

    @PutMapping("/user/{id}")
    public Product updateProductById(@PathVariable Long id, @RequestBody  Product product) {
        return service.updateProductById(product, id);
    }

}
