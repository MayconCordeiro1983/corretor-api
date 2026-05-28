package com.corretor.corretor.controller;

import com.corretor.corretor.dto.PropostaRequest;
import com.corretor.corretor.dto.PropostaResponse;
import com.corretor.corretor.model.Proposta;
import com.corretor.corretor.model.StatusProposta;
import com.corretor.corretor.service.PropostaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/propostas")
@CrossOrigin("*")
public class PropostaController {

    private final PropostaService propostaService;

    public PropostaController(PropostaService propostaService) {
        this.propostaService = propostaService;
    }

    @PostMapping("/imovel/{idImovel}")
    public PropostaResponse criar(
            @PathVariable Long idImovel,
            @Valid @RequestBody PropostaRequest req) {
        Proposta proposta = propostaService.criar(idImovel, req);
        return toResponse(proposta);
    }

    @GetMapping("/minhas")
    public List<PropostaResponse> minhas() {
        return propostaService.listarMinhas()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @PutMapping("/{id}/status")
    public PropostaResponse atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusProposta status) {
        Proposta proposta = propostaService.atualizarStatus(id, status);
        return toResponse(proposta);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        propostaService.deletar(id);
    }

    private PropostaResponse toResponse(Proposta p) {
        return new PropostaResponse(
                p.getId(),
                p.getNomeCliente(),
                p.getTelefoneCliente(),
                p.getValorProposto(),
                p.getMensagem(),
                p.getStatus(),
                p.getDataCriacao(),
                p.getImovel() != null ? p.getImovel().getId() : null,
                p.getImovel() != null ? p.getImovel().getTitulo() : null,
                p.getImovel() != null ? p.getImovel().getEndereco() : null,
                p.getImovel() != null ? p.getImovel().getPreco() : null);
    }
}