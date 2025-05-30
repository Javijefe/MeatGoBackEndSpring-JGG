package com.meatgo.controller;

import com.meatgo.dto.ProductoDTO;
import com.meatgo.dto.ProductoResponseDTO;
import com.meatgo.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/productos")
    public ResponseEntity<ProductoResponseDTO> obtenerProductos() {
        List<ProductoDTO> productosDTO = productoService.obtenerTodosDTO();
        ProductoResponseDTO response = new ProductoResponseDTO(productosDTO);
        return ResponseEntity.ok(response);
    }
}
