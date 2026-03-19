package com.corretor.corretor.service;

import com.corretor.corretor.dto.ImovelRequest;
import com.corretor.corretor.model.Imovel;
import com.corretor.corretor.model.Usuario;
import com.corretor.corretor.repository.ImovelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImovelService {

    private final ImovelRepository imovelRepository;
    private final UsuarioService usuarioService;

    public ImovelService(ImovelRepository imovelRepository, UsuarioService usuarioService) {
        this.imovelRepository = imovelRepository;
        this.usuarioService = usuarioService;
    }

    public Imovel criar(ImovelRequest req) {
        Usuario logado = usuarioService.getUsuarioLogado();

        Imovel i = new Imovel();
        i.setTitulo(req.getTitulo());
        i.setEndereco(req.getEndereco());
        i.setPreco(req.getPreco());
        i.setUsuario(logado);

        return imovelRepository.save(i);
    }
    
    public List<Imovel> listarTodos() {
        return imovelRepository.findAll();
    }

    public List<Imovel> listarMeus() {
        String email = usuarioService.getEmailLogado();
        return imovelRepository.findByUsuarioEmail(email);
    }

    public List<Imovel> buscarPorTitulo(String titulo) {
        String email = usuarioService.getEmailLogado();
        return imovelRepository.findByUsuarioEmailAndTituloContainingIgnoreCase(email, titulo);
    }

    public List<Imovel> buscarPorPrecoMax(Double precoMax) {
        String email = usuarioService.getEmailLogado();
        return imovelRepository.findByUsuarioEmailAndPrecoLessThanEqual(email, precoMax);
    }

    public Imovel atualizar(Long id, ImovelRequest req) {
        Usuario logado = usuarioService.getUsuarioLogado();

        Imovel imovel = imovelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imóvel não encontrado"));

        if (imovel.getUsuario() == null || !imovel.getUsuario().getId().equals(logado.getId())) {
            throw new RuntimeException("Você não pode alterar este imóvel");
        }

        imovel.setTitulo(req.getTitulo());
        imovel.setEndereco(req.getEndereco());
        imovel.setPreco(req.getPreco());

        return imovelRepository.save(imovel);
    }

    public void deletar(Long id) {
        Usuario logado = usuarioService.getUsuarioLogado();

        Imovel imovel = imovelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imóvel não encontrado"));

        if (imovel.getUsuario() == null || !imovel.getUsuario().getId().equals(logado.getId())) {
            throw new RuntimeException("Você não pode deletar este imóvel");
        }

        imovelRepository.delete(imovel);
    }
}