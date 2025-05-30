package com.meatgo.controller;

import com.meatgo.dto.AumentarStockRequestDTO;
import com.meatgo.dto.AumentarStockResponseDTO;
import com.meatgo.service.AdminModificarStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminAumentarStockController {

    @Autowired
    private AdminModificarStockService adminModificarStockService;

    @PostMapping("/aumentar_stock_producto")
    public ResponseEntity<AumentarStockResponseDTO> aumentarStock(@RequestBody AumentarStockRequestDTO request) {
        AumentarStockResponseDTO response = adminModificarStockService.aumentarStockProducto(request);
        if ("ok".equalsIgnoreCase(response.getStatus())) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}
