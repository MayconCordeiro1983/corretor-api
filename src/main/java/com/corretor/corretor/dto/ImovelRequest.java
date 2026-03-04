package com.corretor.corretor.dto;

public class ImovelRequest {
    private String titulo;
    private String endereco;
    private Double preco;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
}