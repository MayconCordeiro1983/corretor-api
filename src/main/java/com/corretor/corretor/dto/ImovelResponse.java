package com.corretor.corretor.dto;

import com.corretor.corretor.model.StatusImovel;

public class ImovelResponse {

    private Long id;

    private String titulo;

    private String endereco;

    private Double preco;

    private String bairro;

    private String cidade;

    private String estado;

    private String cep;

    private String mapaUrl;

    private StatusImovel status;

    private String imagemUrl;

    public ImovelResponse(
            Long id,
            String titulo,
            String endereco,
            Double preco,
            String bairro,
            String cidade,
            String estado,
            String cep,
            String mapaUrl,
            StatusImovel status,
            String imagemUrl) {
        this.id = id;
        this.titulo = titulo;
        this.endereco = endereco;
        this.preco = preco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.mapaUrl = mapaUrl;
        this.status = status;
        this.imagemUrl = imagemUrl;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEndereco() {
        return endereco;
    }

    public Double getPreco() {
        return preco;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }

    public String getMapaUrl() {
        return mapaUrl;
    }

    public StatusImovel getStatus() {
        return status;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }
}