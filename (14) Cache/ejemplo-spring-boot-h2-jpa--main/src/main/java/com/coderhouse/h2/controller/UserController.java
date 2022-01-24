package com.coderhouse.h2.controller;

import com.coderhouse.h2.model.Restaurante;
import com.coderhouse.h2.service.RestauranteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/coder-house")
public class UserController {

    @Autowired
    private RestauranteService service;

    @PostMapping("/restaurante")
    public Restaurante createRestaurante(@RequestBody Restaurante restaurante) {
        return service.createRestaurante(restaurante);
    }

    @PostMapping("/restaurante/toMap")
    public Map<String, Restaurante> restauranteMap(@RequestBody @Validated String restaurante) throws JsonProcessingException{
        return service.restauranteStringToMap(restaurante);
    }

}
