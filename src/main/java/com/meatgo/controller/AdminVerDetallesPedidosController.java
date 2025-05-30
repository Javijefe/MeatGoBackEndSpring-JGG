package com.meatgo.controller;

import com.meatgo.dto.VerDetallesPedidoRequestDTO;
import com.meatgo.dto.VerDetallesPedidoResponseDTO;
import com.meatgo.service.AdminVerDetallesPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class AdminVerDetallesPedidosController {

    @Autowired
    private AdminVerDetallesPedidoService verDetallesPedidoService;

    @PostMapping("/ver_detalles_pedidos")
    public ResponseEntity<VerDetallesPedidoResponseDTO> verDetallesPedidos(@RequestBody VerDetallesPedidoRequestDTO request) {
        try {
            VerDetallesPedidoResponseDTO response = verDetallesPedidoService.verDetallesPedido(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(new VerDetallesPedidoResponseDTO("error: " + e.getMessage(), null));
        }
    }
}
