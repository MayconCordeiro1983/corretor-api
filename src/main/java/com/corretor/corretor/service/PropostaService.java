package com.corretor.corretor.service;

import com.corretor.corretor.dto.PropostaRequest;
import com.corretor.corretor.model.Imovel;
import com.corretor.corretor.model.Proposta;
import com.corretor.corretor.model.StatusProposta;
import com.corretor.corretor.model.Usuario;
import com.corretor.corretor.repository.ImovelRepository;
import com.corretor.corretor.repository.PropostaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {

    private final PropostaRepository propostaRepository;
    private final ImovelRepository imovelRepository;
    private final UsuarioService usuarioService;

    public PropostaService(
            PropostaRepository propostaRepository,
            ImovelRepository imovelRepository,
            UsuarioService usuarioService) {
        this.propostaRepository = propostaRepository;
        this.imovelRepository = imovelRepository;
        this.usuarioService = usuarioService;
    }

    public Proposta criar(Long idImovel, PropostaRequest req) {
        Imovel imovel = imovelRepository.findById(idImovel)
                .orElseThrow(() -> new RuntimeException("Imóvel não encontrado"));

        Proposta proposta = new Proposta();
        proposta.setNomeCliente(req.getNomeCliente());
        proposta.setTelefoneCliente(req.getTelefoneCliente());
        proposta.setValorProposto(req.getValorProposto());
        proposta.setMensagem(req.getMensagem());
        proposta.setStatus(StatusProposta.EM_ANALISE);
        proposta.setImovel(imovel);

        return propostaRepository.save(proposta);
    }

    public List<Proposta> listarMinhas() {
        String email = usuarioService.getEmailLogado();
        return propostaRepository.findByImovelUsuarioEmail(email);
    }

    public Proposta atualizarStatus(Long id, StatusProposta status) {
        Usuario logado = usuarioService.getUsuarioLogado();

        Proposta proposta = propostaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proposta não encontrada"));

        if (proposta.getImovel() == null ||
                proposta.getImovel().getUsuario() == null ||
                !proposta.getImovel().getUsuario().getId().equals(logado.getId())) {
            throw new RuntimeException("Você não pode alterar esta proposta");
        }

        proposta.setStatus(status);

        return propostaRepository.save(proposta);
    }

    public void deletar(Long id) {
        Usuario logado = usuarioService.getUsuarioLogado();

        Proposta proposta = propostaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proposta não encontrada"));

        if (proposta.getImovel() == null ||
                proposta.getImovel().getUsuario() == null ||
                !proposta.getImovel().getUsuario().getId().equals(logado.getId())) {
            throw new RuntimeException("Você não pode deletar esta proposta");
        }

        propostaRepository.delete(proposta);
    }
}