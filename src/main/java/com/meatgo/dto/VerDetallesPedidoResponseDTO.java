package com.meatgo.dto;

import java.util.List;

public class VerDetallesPedidoResponseDTO {
    private String status;
    private List<DetallePedidoDTO> detalles;

    public VerDetallesPedidoResponseDTO() {}

    public VerDetallesPedidoResponseDTO(String status, List<DetallePedidoDTO> detalles) {
        this.status = status;
        this.detalles = detalles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DetallePedidoDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedidoDTO> detalles) {
        this.detalles = detalles;
    }

    public static class DetallePedidoDTO {
        private Integer productoId;
        private Integer cantidad;
        private Double precio;
        private Double subtotal;

        public DetallePedidoDTO() {}

        public DetallePedidoDTO(Integer productoId, Integer cantidad, Double precio, Double subtotal) {
            this.productoId = productoId;
            this.cantidad = cantidad;
            this.precio = precio;
            this.subtotal = subtotal;
        }

        public Integer getProductoId() {
            return productoId;
        }

        public void setProductoId(Integer productoId) {
            this.productoId = productoId;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }

        public Double getPrecio() {
            return precio;
        }

        public void setPrecio(Double precio) {
            this.precio = precio;
        }

        public Double getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(Double subtotal) {
            this.subtotal = subtotal;
        }
    }
}
