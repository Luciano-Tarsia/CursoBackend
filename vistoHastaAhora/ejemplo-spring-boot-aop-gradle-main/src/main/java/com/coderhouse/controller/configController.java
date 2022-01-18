package com.coderhouse.controller;

import com.coderhouse.handle.NombreVacio;
import com.coderhouse.handle.RecursoNoExistente;
import com.coderhouse.model.Cliente;
import com.coderhouse.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api")
public class configController {

    private static final Logger logger = LoggerFactory.getLogger(configController.class);

    @Autowired
    ClienteService clienteService;

    @PostMapping(path = "/users")
    private Cliente createCliente(@RequestBody @Validated Cliente nuevoCliente) throws NombreVacio {
        logger.info("Cargando peticion POST");
        try {
            return clienteService.create(nuevoCliente);
        } catch (NombreVacio nombreVacio) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, nombreVacio.getMessage());
        }
    }

    @GetMapping(path = "/users/{id}")
    private Cliente getCliente(@PathVariable Long id) {
        logger.info("Cargando peticion GET");
        try {
            return clienteService.getCliente(id);
        } catch (RecursoNoExistente recursoNoExistente) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, recursoNoExistente.getMessage());
        }
    }

    @PutMapping(path = "/users")
    private Cliente updateCliente(@RequestBody @Validated Cliente clienteActualizado) {
        logger.info("Cargando peticion PUT");
        try {
            return clienteService.update(clienteActualizado);
        } catch (RecursoNoExistente recursoNoExistente) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, recursoNoExistente.getMessage());
        } catch ( NombreVacio nombreVacio) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, nombreVacio.getMessage());
        }
    }

    @DeleteMapping(path = "/users/{id}")
    private void deleteCliente(@PathVariable Long id) {
        logger.info("Cargando peticion DELETE");
        clienteService.deleteCliente(id);
    }
}
