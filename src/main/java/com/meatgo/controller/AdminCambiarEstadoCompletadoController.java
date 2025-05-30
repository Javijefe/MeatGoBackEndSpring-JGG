package com.meatgo.controller;

import com.meatgo.dto.AdminCambiarEstadoCompletadoRequestDTO;
import com.meatgo.dto.AdminCambiarEstadoCompletadoResponseDTO;
import com.meatgo.service.AdminCambiarEstadoCompletadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class AdminCambiarEstadoCompletadoController {

    @Autowired
    private AdminCambiarEstadoCompletadoService adminCambiarEstadoCompletadoService;

    @PostMapping("/cambiar_estado_pedido_completado")
    public ResponseEntity<AdminCambiarEstadoCompletadoResponseDTO> cambiarEstadoCompletado(
            @RequestBody AdminCambiarEstadoCompletadoRequestDTO request) {
        try {
            AdminCambiarEstadoCompletadoResponseDTO response = adminCambiarEstadoCompletadoService.cambiarEstado(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            AdminCambiarEstadoCompletadoResponseDTO errorResponse = new AdminCambiarEstadoCompletadoResponseDTO(
                    "error",
                    e.getMessage()
            );
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
