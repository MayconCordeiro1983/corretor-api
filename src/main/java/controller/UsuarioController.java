package com.corretor.corretor.controller;

import com.corretor.corretor.dto.LoginRequest;
import com.corretor.corretor.dto.LoginResponse;
import com.corretor.corretor.dto.UsuarioResponse;
import com.corretor.corretor.model.Usuario;
import com.corretor.corretor.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public UsuarioResponse salvar(@RequestBody Usuario usuario) {
        Usuario salvo = service.salvar(usuario);
        return new UsuarioResponse(salvo.getId(), salvo.getNome(), salvo.getEmail());
    }

    @GetMapping
    public List<UsuarioResponse> listar() {	
        return service.listar().stream()
                .map(u -> new UsuarioResponse(u.getId(), u.getNome(), u.getEmail()))
                .toList();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest body) {
        String token = service.login(body.getEmail(), body.getSenha());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @GetMapping("/me")
    public UsuarioResponse me(Authentication authentication) {
        String email = authentication.getName(); // vem do JwtAuthFilter (principal=email)
        Usuario u = service.buscarPorEmail(email);
        return new UsuarioResponse(u.getId(), u.getNome(), u.getEmail());
    }
}