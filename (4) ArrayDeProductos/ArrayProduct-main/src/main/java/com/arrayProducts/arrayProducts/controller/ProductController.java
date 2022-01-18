package com.arrayProducts.arrayProducts.controller;

import com.arrayProducts.arrayProducts.handle.ProductError;
import com.arrayProducts.arrayProducts.module.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private List<Product> productList = new ArrayList<Product>();

    @GetMapping
    public List<Product> getProductList() throws ProductError {
        if(productList.isEmpty()){
            throw new ProductError("No hay productos cargados");
        }
        return productList;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Integer id) throws ProductError {
        for(Product product : productList){
            if(product.getId() == id){
                return product;
            }
        }
        throw new ProductError("No existe producto con ese ID");
    }

    @PostMapping
    public String addProduct(@RequestBody Map<String,String> requestParam) {
        String title = requestParam.get("title");
        String price = requestParam.get("price");
        Product product = new Product(title, Integer.parseInt(price));
        productList.add(product);
        product.setId(productList.size());
        return "Se agrego un nuevo producto con ID: " + productList.size();
    }
}
