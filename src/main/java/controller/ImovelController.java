package com.corretor.corretor.controller;

import com.corretor.corretor.dto.ImovelRequest;
import com.corretor.corretor.dto.ImovelResponse;
import com.corretor.corretor.service.ImovelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imoveis")
@CrossOrigin("*")
public class ImovelController {

    private final ImovelService service;

    public ImovelController(ImovelService service) {
        this.service = service;
    }

    @PostMapping
    public ImovelResponse criar(@RequestBody ImovelRequest req) {
        var salvo = service.criar(req);
        return new ImovelResponse(salvo.getId(), salvo.getTitulo(), salvo.getEndereco(), salvo.getPreco());
    }

    @GetMapping("/meus")
    public List<ImovelResponse> meus() {
        return service.listarMeus().stream()
                .map(i -> new ImovelResponse(i.getId(), i.getTitulo(), i.getEndereco(), i.getPreco()))
                .toList();
    }
}