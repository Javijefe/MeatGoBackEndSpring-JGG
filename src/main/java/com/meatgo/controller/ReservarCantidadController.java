package com.meatgo.controller;

import com.meatgo.dto.ReservarCantidadRequestDTO;
import com.meatgo.service.ReservarCantidadService;
import com.meatgo.service.ReservarCantidadService.ReservarCantidadResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservarCantidadController {

    @Autowired
    private ReservarCantidadService reservarCantidadService;

    @PostMapping("/reservar_cantidad")
    public ResponseEntity<?> reservarCantidad(@RequestBody ReservarCantidadRequestDTO request) {
        try {
            ReservarCantidadResponse response = reservarCantidadService.reservarCantidad(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
