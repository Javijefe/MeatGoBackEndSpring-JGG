package com.meatgo.dto;

public class AumentarStockResponseDTO {
    private String status;
    private String mensaje;
    private double nuevoStock;

    public AumentarStockResponseDTO() {
    }

    public AumentarStockResponseDTO(String status, String mensaje, double nuevoStock) {
        this.status = status;
        this.mensaje = mensaje;
        this.nuevoStock = nuevoStock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public double getNuevoStock() {
        return nuevoStock;
    }

    public void setNuevoStock(double nuevoStock) {
        this.nuevoStock = nuevoStock;
    }
}
