package com.corretor.corretor.dto;

import com.corretor.corretor.model.StatusImovel;

public class ImovelResponse {

    private Long id;
    private String titulo;
    private String endereco;
    private Double preco;
    private StatusImovel status;

    public ImovelResponse(Long id, String titulo, String endereco, Double preco, StatusImovel status) {
        this.id = id;
        this.titulo = titulo;
        this.endereco = endereco;
        this.preco = preco;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getEndereco() { return endereco; }
    public Double getPreco() { return preco; }
    public StatusImovel getStatus() { return status; }
}