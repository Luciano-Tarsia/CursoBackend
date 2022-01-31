package com.arrayProducts.arrayProducts.module;

public class Product {

    private String title;
    private Integer price;
    private Integer id;

    public Product(){}

    public Product(String title, Integer price, Integer id) {
        this.title = title;
        this.price = price;
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
