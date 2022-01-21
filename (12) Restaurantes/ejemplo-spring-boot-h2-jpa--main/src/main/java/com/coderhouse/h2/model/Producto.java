package com.coderhouse.h2.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@ToString
@Getter
@Setter
@Builder
public class Producto {
    private String nombre;
    private Long precio;
}
