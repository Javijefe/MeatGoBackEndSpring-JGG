package com.meatgo.dto;

import java.util.List;

public class CestaResponseDTO {
    private List<ProductoCestaDTO> productos;

    public CestaResponseDTO() {}

    public CestaResponseDTO(List<ProductoCestaDTO> productos) {
        this.productos = productos;
    }

    public List<ProductoCestaDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoCestaDTO> productos) {
        this.productos = productos;
    }

    public static class ProductoCestaDTO {
        private int id_detalle;
        private String producto;
        private double cantidad;
        private double subtotal;

        public ProductoCestaDTO() {}

        public ProductoCestaDTO(int id_detalle, String producto, double cantidad, double subtotal) {
            this.id_detalle = id_detalle;
            this.producto = producto;
            this.cantidad = cantidad;
            this.subtotal = subtotal;
        }

        public int getId_detalle() {
            return id_detalle;
        }

        public void setId_detalle(int id_detalle) {
            this.id_detalle = id_detalle;
        }

        public String getProducto() {
            return producto;
        }

        public void setProducto(String producto) {
            this.producto = producto;
        }

        public double getCantidad() {
            return cantidad;
        }

        public void setCantidad(double cantidad) {
            this.cantidad = cantidad;
        }

        public double getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(double subtotal) {
            this.subtotal = subtotal;
        }
    }
}
