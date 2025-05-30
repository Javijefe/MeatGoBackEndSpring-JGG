package com.meatgo.service;

import com.meatgo.model.Usuario;
import com.meatgo.model.Rol;
import com.meatgo.model.Sesion;
import com.meatgo.repository.UsuarioRepository;
import com.meatgo.repository.SesionRepository;
import com.meatgo.dto.LoginRequest;
import com.meatgo.dto.LoginResponse;
import com.meatgo.dto.RegisterRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final SesionRepository sesionRepository;

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    public UsuarioService(UsuarioRepository usuarioRepository, SesionRepository sesionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.sesionRepository = sesionRepository;
    }

    public LoginResponse iniciarSesion(LoginRequest loginRequest) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(loginRequest.getEmail());

        if (usuarioOpt.isEmpty()) {
            return new LoginResponse("Usuario no encontrado", null);
        }

        Usuario usuario = usuarioOpt.get();

        if (!usuario.getContraseña().equals(loginRequest.getContraseña())) {
            return new LoginResponse("Contraseña incorrecta", null);
        }

        String token = generarToken(usuario);

        Optional<Sesion> sesionOpt = sesionRepository.findTopByUsuarioIdOrderByIdDesc(usuario.getId());

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

        return new LoginResponse("Inicio de sesión correcto", token);
    }

    public LoginResponse registrarse(RegisterRequest registerRequest) {
        if (usuarioRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            return new LoginResponse("Email ya registrado", null);
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEmail(registerRequest.getEmail());
        nuevoUsuario.setContraseña(registerRequest.getContraseña()); 
        nuevoUsuario.setCreadoEn(new Timestamp(System.currentTimeMillis()));

        Rol rol = new Rol();
        rol.setId(registerRequest.getRolId());
        nuevoUsuario.setRol(rol);

        usuarioRepository.save(nuevoUsuario);

        String token = generarToken(nuevoUsuario);

        Sesion sesion = new Sesion();
        sesion.setUsuario(nuevoUsuario);
        sesion.setToken(token);
        sesionRepository.save(sesion);

        return new LoginResponse("Registro exitoso", token);
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
