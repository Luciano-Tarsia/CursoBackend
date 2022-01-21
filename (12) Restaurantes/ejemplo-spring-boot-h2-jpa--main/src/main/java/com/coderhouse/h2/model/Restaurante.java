package com.coderhouse.h2.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@ToString
@Getter
@Setter
@Builder
public class Restaurante {
    @Id
    private Long id;
    private String nombre;
    private Ciudad ciudad;
    private Menu menu;
    private String hora_inicio;
    private String hora_fin;
    private String fecha_creacion;
}
