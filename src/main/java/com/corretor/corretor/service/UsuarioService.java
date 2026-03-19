package com.corretor.corretor.service;

import com.corretor.corretor.model.Usuario;
import com.corretor.corretor.repository.UsuarioRepository;
import com.corretor.corretor.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ HELPER: pega email do usuário autenticado pelo JWT
    public String getEmailLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName(); // retorna o "name" do Authentication (no seu caso, email)
    }

    // ✅ HELPER: pega o usuário logado no banco
    public Usuario getUsuarioLogado() {
        String email = getEmailLogado();
        return buscarPorEmail(email);
    }

    public Usuario salvar(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email não encontrado"));
    }

    public String login(String email, String senha) {
        Usuario usuario = buscarPorEmail(email);

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtUtil.gerarToken(usuario.getEmail());
    }
}