package com.meatgo.controller;

import com.meatgo.dto.CestaResponseDTO;
import com.meatgo.dto.EliminarProductoCestaRequestDTO;
import com.meatgo.service.CestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eliminar_producto_cesta")
public class EliminarProductoController {

    @Autowired
    private CestaService cestaService;

    @PostMapping
    public ResponseEntity<CestaResponseDTO> eliminarProducto(@RequestBody EliminarProductoCestaRequestDTO request) {
        CestaResponseDTO response = cestaService.eliminarProductoCompletoDeCesta(request);
        return ResponseEntity.ok(response);
    }
}
