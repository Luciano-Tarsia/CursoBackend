package com.coderhouse.h2.controller;

import com.coderhouse.h2.model.Restaurante;
import com.coderhouse.h2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coder-house")
public class UserController {

    private final UserService service;

    @PostMapping("/restaurante")
    public Restaurante createRestaurante(@RequestBody Restaurante restaurante) {
        return service.createRestaurante(restaurante);
    }

    @GetMapping("/restaurante/all")
    public List<Restaurante> findRestaurantes() {
        return service.findAll();
    }

    @GetMapping("/restaurante")
    public Restaurante findByName(@RequestParam String name) {
        return service.findByName(name);
    }

    @PutMapping("/restaurante/{id}")
    public Restaurante updateRestauranteById(@PathVariable Long id, @RequestBody  Restaurante restaurante) {
        return service.updateRestauranteById(restaurante, id);
    }

}
