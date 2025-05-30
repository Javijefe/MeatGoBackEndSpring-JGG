package com.meatgo.dto;

public class AdminCambiarEstadoPedidoResponseDTO {
    private String status;
    private String mensaje;

    public AdminCambiarEstadoPedidoResponseDTO() {
    }

    public AdminCambiarEstadoPedidoResponseDTO(String status, String mensaje) {
        this.status = status;
        this.mensaje = mensaje;
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
}
