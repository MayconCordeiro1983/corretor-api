package com.corretor.corretor.dto;

import com.corretor.corretor.model.StatusProposta;

import java.time.LocalDateTime;

public class PropostaResponse {

    private Long id;
    private String nomeCliente;
    private String telefoneCliente;
    private Double valorProposto;
    private String mensagem;
    private StatusProposta status;
    private LocalDateTime dataCriacao;

    private Long imovelId;
    private String imovelTitulo;
    private String imovelEndereco;
    private Double imovelPreco;

    public PropostaResponse(
            Long id,
            String nomeCliente,
            String telefoneCliente,
            Double valorProposto,
            String mensagem,
            StatusProposta status,
            LocalDateTime dataCriacao,
            Long imovelId,
            String imovelTitulo,
            String imovelEndereco,
            Double imovelPreco) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.telefoneCliente = telefoneCliente;
        this.valorProposto = valorProposto;
        this.mensagem = mensagem;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.imovelId = imovelId;
        this.imovelTitulo = imovelTitulo;
        this.imovelEndereco = imovelEndereco;
        this.imovelPreco = imovelPreco;
    }

    public Long getId() {
        return id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public Double getValorProposto() {
        return valorProposto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public StatusProposta getStatus() {
        return status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Long getImovelId() {
        return imovelId;
    }

    public String getImovelTitulo() {
        return imovelTitulo;
    }

    public String getImovelEndereco() {
        return imovelEndereco;
    }

    public Double getImovelPreco() {
        return imovelPreco;
    }
}