package com.meatgo.controller;

import com.meatgo.dto.MisPedidosAcabadosRequestDTO;
import com.meatgo.dto.MisPedidosAcabadosResponseDTO;
import com.meatgo.service.MisPedidosAcabadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MisPedidosAcabadosController {

    @Autowired
    private MisPedidosAcabadosService misPedidosAcabadosService;

    @PostMapping("/ver_pedido_completado")
    public ResponseEntity<MisPedidosAcabadosResponseDTO> verPedidoCompletado(@RequestBody MisPedidosAcabadosRequestDTO request) {
        MisPedidosAcabadosResponseDTO response = misPedidosAcabadosService.verPedidoCompletado(request);
        return ResponseEntity.ok(response);
    }
}
