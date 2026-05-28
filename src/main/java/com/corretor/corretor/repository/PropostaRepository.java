package com.corretor.corretor.repository;

import com.corretor.corretor.model.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    List<Proposta> findByImovelUsuarioEmail(String email);

    List<Proposta> findByImovelId(Long imovelId);
}