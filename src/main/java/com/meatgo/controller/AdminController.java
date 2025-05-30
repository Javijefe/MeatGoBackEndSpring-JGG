package com.meatgo.controller;

import com.meatgo.dto.LoginRequest;
import com.meatgo.dto.LoginResponse;
import com.meatgo.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/iniciar_sesion_admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> iniciarSesionAdmin(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = adminService.iniciarSesionAdmin(loginRequest);

        if (response.getToken() == null) {
            int status = response.getMessage().contains("permisos") ? 403 : 400;
            return ResponseEntity.status(status).body(response);
        }

        return ResponseEntity.ok(response);
    }
}
