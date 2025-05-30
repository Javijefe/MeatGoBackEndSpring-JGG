package com.meatgo.dto;

public class EliminarProductoCestaRequestDTO {
    private String token;
    private int id_detalle;

    public EliminarProductoCestaRequestDTO() {
    }

    public EliminarProductoCestaRequestDTO(String token, int id_detalle) {
        this.token = token;
        this.id_detalle = id_detalle;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId_detalle() {
        return id_detalle;
    }   

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }
}
