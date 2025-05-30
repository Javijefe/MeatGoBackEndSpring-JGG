package com.meatgo.dto;

import java.util.List;

public class ProductoResponseDTO {
    private List<ProductoDTO> productos;

    public ProductoResponseDTO() {}

    public ProductoResponseDTO(List<ProductoDTO> productos) {
        this.productos = productos;
    }

    public List<ProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
    }
}
