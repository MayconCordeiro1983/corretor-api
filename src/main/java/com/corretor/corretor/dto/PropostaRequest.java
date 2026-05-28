package com.corretor.corretor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PropostaRequest {

    @NotBlank(message = "O nome do cliente é obrigatório")
    private String nomeCliente;

    @NotBlank(message = "O telefone do cliente é obrigatório")
    private String telefoneCliente;

    @NotNull(message = "O valor da proposta é obrigatório")
    @Positive(message = "O valor da proposta deve ser maior que zero")
    private Double valorProposto;

    private String mensagem;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public Double getValorProposto() {
        return valorProposto;
    }

    public void setValorProposto(Double valorProposto) {
        this.valorProposto = valorProposto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}