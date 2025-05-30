package com.meatgo.controller;

import com.meatgo.dto.AdminCambiarEstadoPedidoRequestDTO;
import com.meatgo.dto.AdminCambiarEstadoPedidoResponseDTO;
import com.meatgo.service.AdminCambiarEstadoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminCambiarEstadoPedidoController {

    @Autowired
    private AdminCambiarEstadoPedidoService adminCambiarEstadoPedidoService;

    @PostMapping("/cambiar_estado_pedido_pendiente")
    public ResponseEntity<AdminCambiarEstadoPedidoResponseDTO> cambiarEstado(@RequestBody AdminCambiarEstadoPedidoRequestDTO request) {
        try {
            AdminCambiarEstadoPedidoResponseDTO response = adminCambiarEstadoPedidoService.cambiarEstado(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                new AdminCambiarEstadoPedidoResponseDTO("error", e.getMessage())
            );
        }
    }
}
