package com.meatgo.controller;

import com.meatgo.dto.TipoCarneResponseDTO;
import com.meatgo.dto.TipoCarneDTO;
import com.meatgo.service.TipoCarneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TipoCarneController {

    private final TipoCarneService tipoCarneService;

    public TipoCarneController(TipoCarneService tipoCarneService) {
        this.tipoCarneService = tipoCarneService;
    }

    @GetMapping("/tipos_carne")
    public ResponseEntity<TipoCarneResponseDTO> obtenerTiposCarne() {
        List<TipoCarneDTO> tiposCarneDTO = tipoCarneService.obtenerTodosDTO();
        TipoCarneResponseDTO response = new TipoCarneResponseDTO(tiposCarneDTO);
        return ResponseEntity.ok(response);
    }
}
