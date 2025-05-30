package com.meatgo.dto;

public class MisPedidosAcabadosRequestDTO {
    private String token;

    public MisPedidosAcabadosRequestDTO() {}

    public MisPedidosAcabadosRequestDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
