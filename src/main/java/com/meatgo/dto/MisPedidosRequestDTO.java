package com.meatgo.dto;

public class MisPedidosRequestDTO {
    private String token;

    public MisPedidosRequestDTO() {
    }

    public MisPedidosRequestDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
