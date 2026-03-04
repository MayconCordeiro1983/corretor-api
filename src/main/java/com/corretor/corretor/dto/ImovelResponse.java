package com.corretor.corretor.dto;

public class ImovelResponse {

    private Long id;
    private String titulo;
    private String endereco;
    private Double preco;

    public ImovelResponse(Long id, String titulo, String endereco, Double preco) {
        this.id = id;
        this.titulo = titulo;
        this.endereco = endereco;
        this.preco = preco;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getEndereco() { return endereco; }
    public Double getPreco() { return preco; }
}