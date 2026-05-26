package com.corretor.corretor.dto;

import com.corretor.corretor.model.StatusImovel;

public class ImovelResponse {

    private Long id;

    private String titulo;

    private String endereco;

    private Double preco;

    private StatusImovel status;

    private String imagemUrl;

    public ImovelResponse(
            Long id,
            String titulo,
            String endereco,
            Double preco,
            StatusImovel status,
            String imagemUrl
    ){

        this.id=id;
        this.titulo=titulo;
        this.endereco=endereco;
        this.preco=preco;
        this.status=status;
        this.imagemUrl=imagemUrl;

    }

    public Long getId(){
        return id;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getEndereco(){
        return endereco;
    }

    public Double getPreco(){
        return preco;
    }

    public StatusImovel getStatus(){
        return status;
    }

    public String getImagemUrl(){
        return imagemUrl;
    }

}