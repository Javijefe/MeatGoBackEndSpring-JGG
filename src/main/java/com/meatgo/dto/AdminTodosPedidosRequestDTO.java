package com.meatgo.dto;

public class AdminTodosPedidosRequestDTO {

    private String token;

    public AdminTodosPedidosRequestDTO() {}

    public AdminTodosPedidosRequestDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
