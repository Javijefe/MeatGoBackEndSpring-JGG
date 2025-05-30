package com.meatgo.service;

import com.meatgo.dto.LoginRequest;
import com.meatgo.dto.LoginResponse;
import com.meatgo.model.Sesion;
import com.meatgo.model.Usuario;
import com.meatgo.repository.SesionRepository;
import com.meatgo.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class AdminService {

    private final UsuarioRepository usuarioRepository;
    private final SesionRepository sesionRepository;

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    public AdminService(UsuarioRepository usuarioRepository, SesionRepository sesionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.sesionRepository = sesionRepository;
    }

    public LoginResponse iniciarSesionAdmin(LoginRequest loginRequest) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(loginRequest.getEmail());

        if (usuarioOpt.isEmpty()) {
            return new LoginResponse("Correo o contraseña incorrectos", null);
        }

        Usuario usuario = usuarioOpt.get();

        if (!usuario.getContraseña().equals(loginRequest.getContraseña())) {
            return new LoginResponse("Correo o contraseña incorrectos", null);
        }

        if (usuario.getRol() == null || usuario.getRol().getId() != 1) {
            return new LoginResponse("No tienes permisos para acceder como admin", null);
        }

        Optional<Sesion> sesionOpt = sesionRepository.findTopByUsuarioIdOrderByIdDesc(usuario.getId());

        String token = generarToken(usuario);
        Sesion sesion;

        if (sesionOpt.isPresent()) {
            sesion = sesionOpt.get();
            sesion.setToken(token);
            sesionRepository.save(sesion);
        } else {
            sesion = new Sesion();
            sesion.setUsuario(usuario);
            sesion.setToken(token);
            sesionRepository.save(sesion);
        }

        System.out.println("Ha iniciado sesión el administrador " + usuario.getEmail());

        return new LoginResponse("Inicio de sesión admin exitoso", token);
    }

    private String generarToken(Usuario usuario) {
        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .setIssuedAt(ahora)
                .setExpiration(expiracion)
                .claim("rol", usuario.getRol() != null ? usuario.getRol().getNombre() : "desconocido")
                .signWith(key)
                .compact();
    }
}
