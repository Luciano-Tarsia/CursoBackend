package com.coderhouse.service;

import com.coderhouse.handle.ExceptionUsuario;
import com.coderhouse.model.Factory;
import com.coderhouse.model.Usuario;
import com.coderhouse.repository.MongoRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Map;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    MongoTemplate mongoTemplate;

    private final ObjectMapper mapper;

    @Autowired
    private MongoRepository mongoRepository;
    Factory factory;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @PostConstruct
    private void PostConstruct() {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
    }

    private String mappearAString(Usuario usuario) throws JsonProcessingException {
        String usuarioString = mapper.writeValueAsString(usuario);
        logger.info("Usuario mapeado a string: \n" + usuarioString);
        return usuarioString;
    }

    private Map<String, Usuario> mappearAMap(String usuarioString) throws JsonProcessingException {
        Map<String, Usuario> usuarioMap = mapper.readValue(usuarioString, Map.class);
        logger.info("Usuario mapeado a map: \n" + usuarioMap);
        return usuarioMap;
    }

    private Usuario mappearAClase(String usuarioString) throws JsonProcessingException {
        var usuarioClase = mapper.readValue(usuarioString, Usuario.class);
        logger.info("Usuario mapeado a clase: \n" + usuarioClase);
        return usuarioClase;
    }

    public Usuario crearUsuario(Usuario usuario){
        logger.info("Guardando");
        Usuario usuarioDeFactory = Factory.crearUsuario(usuario.getNombre(), usuario.getTipo());
        if(usuarioDeFactory == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tipo de usuario ingresado no es valido");
        }
        try {
            String usuarioString = mappearAString(usuarioDeFactory);
            mappearAClase(usuarioString);
            mappearAMap(usuarioString);
            Usuario usuarioNuevo =  mongoRepository.save(usuarioDeFactory, "usuarios");
            return usuarioNuevo;
        } catch (JsonProcessingException e) {
            logger.error("Error convirtiendo a string", e);
        }
        return usuario;
    }

    public  Usuario getUsuarioById(String id) throws JsonProcessingException {
        Usuario usuario = mongoRepository.findById(id);
        return usuario;
    }

    private Usuario actualizar(Usuario usuario){
        try {
            String usuarioString = mappearAString(usuario);
            logger.info(usuarioString);
            usuario.setActualizacion("Chau");
            Usuario usuarioActualizado = mongoRepository.save(usuario, "usuarios");
            return usuarioActualizado;
        } catch (JsonProcessingException e) {
            logger.error("Error convirtiendo a string", e);
        }
        return usuario;
    }

    public  Usuario updateUsuario(Usuario usuario) throws JsonProcessingException, ExceptionUsuario {
        logger.info("Actualizando");
        Usuario usuarioDeFactory = factory.crearUsuario(usuario.getNombre(), usuario.getTipo());
        if(usuarioDeFactory == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ingrese un type de usuario valido");
        }
        if(mongoRepository.findById(usuario.getId()) != null){
            return actualizar(mongoRepository.findById(usuario.getId()));
        }else {
            throw new ExceptionUsuario("El usuario que se intentó actualizar no existe");
        }
    }

    public void deleteByName(String nombre) throws ExceptionUsuario {
        try {
            Usuario usuario = mongoRepository.findOne((new Query(where("nombre").is(nombre))));
            mongoRepository.findAndRemove((new Query(where("nombre").is(usuario.getNombre()))));
        }catch(NullPointerException n){
            throw new ExceptionUsuario("No se ha encontrado un usuario con ese nombre");
        }
    }

}
