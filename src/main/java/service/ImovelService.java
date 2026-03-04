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

    public List<Imovel> listarMeus() {
        String email = usuarioService.getEmailLogado();
        return imovelRepository.findByUsuarioEmail(email);
    }
}