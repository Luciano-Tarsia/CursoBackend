package com.coderhouse.observerpattern.domain;

public class UserConfig {

    private String rol;
    private String email;
    private String phone;

    public UserConfig() {
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public void setEmail(String rol) {
        this.email = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String rol) {
        this.phone = rol;
    }

    public String getPhone() {
        return phone;
    }
}
