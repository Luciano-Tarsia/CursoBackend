package com.coderhouse.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("productos")
public class Product {

    @Id
    private Long id;
    private String nombre;
    private String categoria;
    private Long precio;
    private Long stock;
    private Long stock2;

    public Product(){};

    public Product(Long id, String nombre, String categoria, Long precio, Long stock, Long stock2){
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
        this.stock2 = stock2;
    };

    public Long getId(){return id;}

    public void setId(Long idAct){this.id = idAct;}

    public String getNombre(){return nombre;}

    public void setNombre(String nombreAct){this.nombre = nombreAct;}

    public String getCategoria(){return categoria;}

    public void setCategoria(String categoriaAct){this.categoria = categoriaAct;}

    public Long getPrecio(){return precio;}

    public void setPrecio(Long precioAct){this.precio = precioAct;}

    public Long getStock(){return stock;}

    public void setStock(Long stockAct){this.stock = stockAct;}

    public Long getStock2(){return stock2;}

    public void setStock2(Long stock2Act){this.stock2 = stock2Act;}
}
