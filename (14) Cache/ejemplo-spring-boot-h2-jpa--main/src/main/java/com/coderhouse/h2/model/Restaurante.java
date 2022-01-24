package com.coderhouse.h2.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

@ToString
@Getter
@Setter
@Builder
@Document("restaurantes")
public class Restaurante implements Serializable {
    @Id
    private Long id;
    private String nombre;
    private Ciudad ciudad;
    private Menu menu;
    private String hora_inicio;
    private String hora_fin;
    private String fecha_creacion;
}
