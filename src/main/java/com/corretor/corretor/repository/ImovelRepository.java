package com.corretor.corretor.repository;

import com.corretor.corretor.model.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImovelRepository extends JpaRepository<Imovel, Long> {

    List<Imovel> findByUsuarioEmail(String email);

    List<Imovel> findByUsuarioEmailAndTituloContainingIgnoreCase(String email, String titulo);

    List<Imovel> findByUsuarioEmailAndPrecoLessThanEqual(String email, Double preco);
}