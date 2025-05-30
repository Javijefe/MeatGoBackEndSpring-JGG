package com.meatgo.dto;

import java.util.List;

public class AdminTodosPedidosResponseDTO {

    private List<PedidoResponseDTO> pedidos;

    public AdminTodosPedidosResponseDTO() {}

    public AdminTodosPedidosResponseDTO(List<PedidoResponseDTO> pedidos) {
        this.pedidos = pedidos;
    }

    public List<PedidoResponseDTO> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoResponseDTO> pedidos) {
        this.pedidos = pedidos;
    }

    public static class PedidoResponseDTO {

        private Integer idPedido;
        private Integer idUsuario;
        private String fecha;  
        private String estado;

        public PedidoResponseDTO() {}

        public PedidoResponseDTO(Integer idPedido, Integer idUsuario, String fecha, String estado) {
            this.idPedido = idPedido;
            this.idUsuario = idUsuario;
            this.fecha = fecha;
            this.estado = estado;
        }

        public Integer getIdPedido() {
            return idPedido;
        }

        public void setIdPedido(Integer idPedido) {
            this.idPedido = idPedido;
        }

        public Integer getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(Integer idUsuario) {
            this.idUsuario = idUsuario;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }
    }
}
