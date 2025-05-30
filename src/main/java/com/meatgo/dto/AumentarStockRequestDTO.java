package com.meatgo.dto;

public class AumentarStockRequestDTO {
    private int productoId;
    private double cantidad;
    private String token;

    public AumentarStockRequestDTO() {
    }

    public AumentarStockRequestDTO(int productoId, double cantidad, String token) {
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.token = token;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
