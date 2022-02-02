package com.coderhouse.model;

public class Factory {

    public static Usuario crearUsuario(String nombre, String tipo){
        if (tipo == "ADMIN"){
            return new Admin(nombre, tipo);
        }else if (tipo == "EDITOR"){
            return new Editor(nombre, tipo);
        }else if (tipo == "CLIENT"){
            return new Client(nombre, tipo);
        }else {
            return null;
        }
    }

}
