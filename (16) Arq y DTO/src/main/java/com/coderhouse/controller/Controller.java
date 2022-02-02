package com.coderhouse.controller;

import com.coderhouse.handle.ExceptionUsuario;
import com.coderhouse.model.Usuario;
import com.coderhouse.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(path = "/coder")
public class Controller {

    private static final Logger log = LoggerFactory.getLogger(Controller.class);
    @Autowired
    UserService service;

    @PostMapping(path = "/usuario")
    private Usuario crearUsuario(@RequestBody @Validated Usuario nuevoUsuario) {
        return service.crearUsuario(nuevoUsuario);
    }


    @GetMapping(path = "/usuario/{id}")
    private Usuario getUsuarioById(@PathVariable String id, HttpSession session) throws ResponseStatusException, JsonProcessingException {
        session.setAttribute("test", "prueba");
        return service.getUsuarioById(id);
    }

    @PutMapping(path = "/usuario")
    private Usuario updateUsuario(@RequestBody @Validated Usuario usuario) throws ResponseStatusException, JsonProcessingException, ExceptionUsuario {
        try {
            return service.updateUsuario(usuario);
        }catch(ExceptionUsuario exceptionUsuario){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exceptionUsuario.getMessage());
        }
    }

    @DeleteMapping(path = "/usuario/{nombre}")
    private void deleteUsuario(@PathVariable String nombre) throws ExceptionUsuario {
        try{
            service.deleteByName(nombre);
        }catch(ExceptionUsuario exceptionUsuario){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exceptionUsuario.getMessage());
        }

    }
    
}
