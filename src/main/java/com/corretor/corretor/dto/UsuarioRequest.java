package com.corretor.corretor.dto;

import jakarta.validation.constraints.*;

public class UsuarioRequest {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, message = "Nome deve ter pelo menos 2 caracteres")
    private String nome;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String senha;

    // getters e setters
}