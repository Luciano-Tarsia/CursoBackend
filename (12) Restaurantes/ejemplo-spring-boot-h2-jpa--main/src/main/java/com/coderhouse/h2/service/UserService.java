package com.coderhouse.h2.service;

import com.coderhouse.h2.model.Restaurante;

import java.util.List;

public interface UserService {

    Restaurante createRestaurante(Restaurante restaurante);
    Restaurante updateRestauranteById(Restaurante restaurante, Long id);
    Restaurante findByName(String name);
    List<Restaurante> findAll();

}
