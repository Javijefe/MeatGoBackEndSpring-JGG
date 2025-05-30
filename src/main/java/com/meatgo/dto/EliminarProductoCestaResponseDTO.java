package com.meatgo.dto;

public class EliminarProductoCestaResponseDTO {
    private String status;
    private String mensaje;
    private double cantidadRestauradaAStock;
    private double stockActual;
    private double reservadoCestasActual;

    public EliminarProductoCestaResponseDTO() {
    }

    public EliminarProductoCestaResponseDTO(String status, String mensaje, double cantidadRestauradaAStock, double stockActual, double reservadoCestasActual) {
        this.status = status;
        this.mensaje = mensaje;
        this.cantidadRestauradaAStock = cantidadRestauradaAStock;
        this.stockActual = stockActual;
        this.reservadoCestasActual = reservadoCestasActual;
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

    public double getCantidadRestauradaAStock() {
        return cantidadRestauradaAStock;
    }

    public void setCantidadRestauradaAStock(double cantidadRestauradaAStock) {
        this.cantidadRestauradaAStock = cantidadRestauradaAStock;
    }

    public double getStockActual() {
        return stockActual;
    }

    public void setStockActual(double stockActual) {
        this.stockActual = stockActual;
    }

    public double getReservadoCestasActual() {
        return reservadoCestasActual;
    }

    public void setReservadoCestasActual(double reservadoCestasActual) {
        this.reservadoCestasActual = reservadoCestasActual;
    }
}
