package com.corretor.corretor.controller;

import com.corretor.corretor.dto.LoginRequest;
import com.corretor.corretor.dto.UsuarioRequest;
import com.corretor.corretor.model.Usuario;
import com.corretor.corretor.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.Map;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> criar(@Valid @RequestBody UsuarioRequest request) {
        return ResponseEntity.ok(usuarioService.criar(request));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        String token = usuarioService.login(request);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/me")
public ResponseEntity<?> me() {
    return ResponseEntity.ok(usuarioService.getUsuarioLogado());
}

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @GetMapping("/email")
public ResponseEntity<Map<String, Boolean>> verificarEmail(@RequestParam String email) {
    return ResponseEntity.ok(Map.of("existe", usuarioService.emailExiste(email)));
}

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody UsuarioRequest request) {
        return ResponseEntity.ok(usuarioService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}