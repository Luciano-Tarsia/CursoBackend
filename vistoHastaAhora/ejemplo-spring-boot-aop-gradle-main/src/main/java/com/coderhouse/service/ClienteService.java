package com.coderhouse.service;

import com.coderhouse.handle.NombreVacio;
import com.coderhouse.handle.RecursoNoExistente;
import com.coderhouse.model.Cliente;
import com.coderhouse.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coderhouse.annotations.CustomMethodAnnotation;

import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);


    public Cliente create (Cliente nuevoCliente) throws NombreVacio {
        if(!(nuevoCliente.getNombre() == "" || nuevoCliente.getNombre() == null)) {
            nuevoCliente.setEstado(true);
            logger.info("Cliente " + nuevoCliente.getId() + " creado");
            return repository.save(nuevoCliente);
        }
        throw new NombreVacio("El nombre no puede estar vacio");
    }
    @CustomMethodAnnotation
    public Cliente update (Cliente clienteActualizado) throws RecursoNoExistente, NombreVacio {
        if(clienteActualizado.getNombre() == "" || clienteActualizado.getNombre() == null){
            throw new NombreVacio("El nombre no puede estar vacio");
        }
        Optional<Cliente> client =  repository.findById(clienteActualizado.getId());
        if(client.isEmpty()){
            logger.info("Cliente no encontrado");
            throw new RecursoNoExistente("El cliente que esta buscando no existe");
        }
        return repository.save(clienteActualizado);
    }

    public Cliente getCliente (Long id) throws RecursoNoExistente{
        Optional<Cliente> client =  repository.findById(id);
        if(client.isEmpty()){
            logger.info("Cliente no encontrado");
            throw new RecursoNoExistente("El cliente que esta buscando no existe");
        }
        return client.get();
    }
    @CustomMethodAnnotation
    public void deleteCliente (Long id){
        repository.deleteById(id);
    }
}
