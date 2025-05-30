package com.meatgo.dto;

public class CestaRequestDTO {
    private String token;

    public CestaRequestDTO() {}

    public CestaRequestDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}


