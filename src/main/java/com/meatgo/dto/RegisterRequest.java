package com.meatgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterRequest {
    private String email;
    private String contraseña;

    @JsonProperty("rol_id") 
    private Integer rolId;

    public RegisterRequest() {}

    public RegisterRequest(String email, String contraseña, Integer rolId) {
        this.email = email;
        this.contraseña = contraseña;
        this.rolId = rolId;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Integer getRolId() {
        return rolId;
    }
    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }
}
