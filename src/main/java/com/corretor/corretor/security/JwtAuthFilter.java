package com.corretor.corretor.security;

import com.corretor.corretor.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;

    public JwtAuthFilter(JwtUtil jwtUtil, UsuarioRepository usuarioRepository) {
        this.jwtUtil = jwtUtil;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        System.out.println(">>> PATH: " + request.getServletPath());
        System.out.println(">>> AUTH HEADER: " + authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            System.out.println(">>> TOKEN RECEBIDO: " + token);

            boolean valido = jwtUtil.tokenValido(token);
            System.out.println(">>> TOKEN VÁLIDO? " + valido);

            if (valido) {
                String email = jwtUtil.extrairEmail(token);
                System.out.println(">>> EMAIL EXTRAÍDO: " + email);

                boolean existe = usuarioRepository.findByEmail(email).isPresent();
                System.out.println(">>> USUÁRIO EXISTE? " + existe);

                if (existe && SecurityContextHolder.getContext().getAuthentication() == null) {
                    var authentication = new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            Collections.emptyList()
                    );

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    System.out.println(">>> AUTHENTICATION SETADO COM SUCESSO");
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    // 🔽 COLOQUE AQUI (fora do método acima)
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.equals("/usuarios/login")
                || (path.equals("/usuarios") && request.getMethod().equals("POST"));
    }
}