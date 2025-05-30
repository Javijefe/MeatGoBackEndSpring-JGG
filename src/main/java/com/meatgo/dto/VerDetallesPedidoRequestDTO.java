package com.meatgo.dto;

public class VerDetallesPedidoRequestDTO {
    private String token;
    private Integer idPedido;

    public VerDetallesPedidoRequestDTO() {}

    public VerDetallesPedidoRequestDTO(String token, Integer idPedido) {
        this.token = token;
        this.idPedido = idPedido;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }
}
