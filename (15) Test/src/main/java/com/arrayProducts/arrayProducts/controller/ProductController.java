package com.arrayProducts.arrayProducts.controller;

import com.arrayProducts.arrayProducts.handle.ProductError;
import com.arrayProducts.arrayProducts.module.Product;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {
    private List<Product> productList = new ArrayList<Product>();
    private Integer id = 0;

    @PostConstruct
    private void addProducts(){
        Product product = new Product("Harina", (Integer)10 , (Integer) productList.size());
        productList.add(product);
    }

    @GetMapping(path = "/productos")
    public List<Product> getProductList() throws ProductError {
        if(productList.isEmpty()){
            throw new ProductError("No hay productos cargados");
        }
        return productList;
    }

    @GetMapping("/productos/{id}")
    public Product getProduct(@PathVariable Integer id) throws ProductError {
        for(Product product : productList){
            if(product.getId() == id){
                return product;
            }
        }
        throw new ProductError("No existe producto con ese ID");
    }

    @PostMapping(path = "/productos")
    public Product addProduct(@RequestBody Map<String,String> requestParam) {
        String title = requestParam.get("title");
        String price = requestParam.get("price");
        Product product = new Product(title, Integer.parseInt(price), productList.size());
        productList.add(product);
        product.setId(productList.size());
        return product;
    }
}
