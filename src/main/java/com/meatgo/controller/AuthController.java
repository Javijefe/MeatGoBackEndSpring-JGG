package com.meatgo.controller;

import com.meatgo.dto.LoginRequest;
import com.meatgo.dto.LoginResponse;
import com.meatgo.dto.RegisterRequest;
import com.meatgo.service.UsuarioService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrarse")
    public ResponseEntity<Void> registerUser (@RequestBody RegisterRequest registerRequest) {
        try {
            usuarioService.registrarse(registerRequest);
            return ResponseEntity.ok().build();  
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();  
        }
    }

    @PostMapping("/iniciar_sesion")
    public ResponseEntity<LoginResponse> loginUser (@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse loginResponse = usuarioService.iniciarSesion(loginRequest);
            return ResponseEntity.ok(loginResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(new LoginResponse("Error: " + e.getMessage(), null));
        }
    }
}
