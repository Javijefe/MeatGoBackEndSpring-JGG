package com.meatgo.controller;

import com.meatgo.dto.MisPedidosRequestDTO;
import com.meatgo.dto.MisPedidosResponseDTO;
import com.meatgo.service.MisPedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MisPedidosController {

    @Autowired
    private MisPedidosService misPedidosService;

    @PostMapping("ver_solicitud")
    public ResponseEntity<MisPedidosResponseDTO> verPedidoSolicitud(@RequestBody MisPedidosRequestDTO request) {
        MisPedidosResponseDTO response = misPedidosService.verSolicitud(request);
        return ResponseEntity.ok(response);
    }
}
