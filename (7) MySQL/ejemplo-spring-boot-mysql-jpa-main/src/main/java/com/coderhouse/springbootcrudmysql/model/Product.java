package com.coderhouse.springbootcrudmysql.model;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String categoria;
    private int stock;

    public Product(){};

    public Product(String nombre, String categoria, int stock) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.stock = stock;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombreAct){
        this.nombre = nombreAct;
    }

    public String getCategoria(){
        return categoria;
    }

    public void setCategoria(String categoriaAct){
        this.categoria = categoriaAct;
    }

    public int getStock(){
        return stock;
    }

    public void setStock(int stockAct){
        this.stock = stockAct;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long idAct){
        this.id = idAct;
    }
}
