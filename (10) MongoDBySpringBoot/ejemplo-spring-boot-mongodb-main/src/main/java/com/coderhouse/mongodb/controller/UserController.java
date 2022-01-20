package com.coderhouse.mongodb.controller;

import com.coderhouse.mongodb.model.Product;
import com.coderhouse.mongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coder-house")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/product")
    public Product createProduct(@RequestBody Product product) {
        return service.createProduct(product);
    }

    @GetMapping("/product/all-products")
    public List<Product> findProducts() {
        return service.findAll();
    }

    @GetMapping("/product")
    public Product findByName(@RequestParam String nombre) {
        return service.findByNombre(nombre);
    }

    @GetMapping("/productGreaters")
    public List<Product> findProductByPrecioGreater(@RequestParam Long precio) {
        return service.findByPrecioGreaterThan(precio);
    }

    @GetMapping("/product/all")
    public List<Product> findAllByCategoriaSortedByAge(
            @RequestParam String categoria,
            @RequestParam String orderBy,
            @RequestParam int limit) {
        return service.findAllByCategoriaSortedLimit(categoria, orderBy, limit);
    }

}
