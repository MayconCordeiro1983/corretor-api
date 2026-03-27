package com.corretor.corretor.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.corretor.corretor.dto.LoginRequest;
import com.corretor.corretor.dto.UsuarioRequest;
import com.corretor.corretor.model.Usuario;
import com.corretor.corretor.repository.UsuarioRepository;
import com.corretor.corretor.security.JwtUtil;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public Usuario criar(UsuarioRequest request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));

        return usuarioRepository.save(usuario);
    }

    public String login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        if (!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha inválida");
        }

        return jwtUtil.gerarToken(usuario.getEmail());
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario atualizar(Long id, UsuarioRequest request) {
        Usuario usuario = buscarPorId(id);

        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());

        return usuarioRepository.save(usuario);
    }

    public boolean emailExiste(String email) {
        return usuarioRepository.findByEmail(email).isPresent();
    }

    public void deletar(Long id) {
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }

    public String getEmailLogado() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Usuario getUsuarioLogado() {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    return usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}