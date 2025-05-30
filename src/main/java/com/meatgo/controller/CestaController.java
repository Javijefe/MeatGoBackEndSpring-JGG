package com.meatgo.controller;

import com.meatgo.dto.CestaRequestDTO;
import com.meatgo.dto.CestaResponseDTO;
import com.meatgo.service.CestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cesta")
public class CestaController {

    @Autowired
    private CestaService cestaService;

    @PostMapping
    public ResponseEntity<CestaResponseDTO> obtenerProductosCesta(@RequestBody CestaRequestDTO request) {
        CestaResponseDTO response = cestaService.obtenerProductosCesta(request);
        return ResponseEntity.ok(response);
    }
}
