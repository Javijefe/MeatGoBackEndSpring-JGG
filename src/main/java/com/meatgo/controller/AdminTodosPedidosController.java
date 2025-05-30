package com.meatgo.controller;

import com.meatgo.dto.AdminTodosPedidosRequestDTO;
import com.meatgo.dto.AdminTodosPedidosResponseDTO;
import com.meatgo.service.AdminTodosPedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Define el path base para endpoints admin (ajústalo si quieres)
@RestController

public class AdminTodosPedidosController {

    @Autowired
    private AdminTodosPedidosService adminTodosPedidosService;

    @PostMapping("/ver_todos_pedidos")
    public ResponseEntity<?> verTodosPedidos(@RequestBody AdminTodosPedidosRequestDTO request) {
        try {
            AdminTodosPedidosResponseDTO response = adminTodosPedidosService.verTodosPedidos(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Token inválido")) {
                return ResponseEntity.status(401).body(e.getMessage());
            } else if (e.getMessage().contains("permisos")) {
                return ResponseEntity.status(403).body(e.getMessage());
            } else {
                return ResponseEntity.status(500).body("Error interno del servidor");
            }
        }
    }
}
