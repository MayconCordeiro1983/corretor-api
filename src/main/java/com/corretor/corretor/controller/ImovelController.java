package com.corretor.corretor.controller;

import com.corretor.corretor.dto.ImovelRequest;
import com.corretor.corretor.dto.ImovelResponse;
import com.corretor.corretor.service.ImovelService;
import jakarta.validation.Valid;
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
    public ImovelResponse criar(@Valid @RequestBody ImovelRequest req) {
        var salvo = service.criar(req);

        return new ImovelResponse(
                salvo.getId(),
                salvo.getTitulo(),
                salvo.getEndereco(),
                salvo.getPreco(),
                salvo.getStatus()
        );
    }

    @GetMapping("/meus")
    public List<ImovelResponse> meus() {
        return service.listarMeus().stream()
                .map(i -> new ImovelResponse(
                        i.getId(),
                        i.getTitulo(),
                        i.getEndereco(),
                        i.getPreco(),
                        i.getStatus()
                ))
                .toList();
    }

    @GetMapping("/buscar")
    public List<ImovelResponse> buscar(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Double precoMax) {

        if (titulo != null && !titulo.isBlank()) {
            return service.buscarPorTitulo(titulo).stream()
                    .map(i -> new ImovelResponse(
                            i.getId(),
                            i.getTitulo(),
                            i.getEndereco(),
                            i.getPreco(),
                            i.getStatus()
                    ))
                    .toList();
        }

        if (precoMax != null) {
            return service.buscarPorPrecoMax(precoMax).stream()
                    .map(i -> new ImovelResponse(
                            i.getId(),
                            i.getTitulo(),
                            i.getEndereco(),
                            i.getPreco(),
                            i.getStatus()
                    ))
                    .toList();
        }

        return service.listarMeus().stream()
                .map(i -> new ImovelResponse(
                        i.getId(),
                        i.getTitulo(),
                        i.getEndereco(),
                        i.getPreco(),
                        i.getStatus()
                ))
                .toList();
    }

    @GetMapping("/publicos")
    public List<ImovelResponse> publicos() {
        return service.listarPublicos().stream()
                .map(i -> new ImovelResponse(
                        i.getId(),
                        i.getTitulo(),
                        i.getEndereco(),
                        i.getPreco(),
                        i.getStatus()
                ))
                .toList();
    }

    @GetMapping("/publicos/buscar")
    public List<ImovelResponse> buscarPublicos(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Double precoMax) {

        if (titulo != null && !titulo.isBlank()) {
            return service.buscarPublicoPorTitulo(titulo).stream()
                    .map(i -> new ImovelResponse(
                            i.getId(),
                            i.getTitulo(),
                            i.getEndereco(),
                            i.getPreco(),
                            i.getStatus()
                    ))
                    .toList();
        }

        if (precoMax != null) {
            return service.buscarPublicoPorPrecoMax(precoMax).stream()
                    .map(i -> new ImovelResponse(
                            i.getId(),
                            i.getTitulo(),
                            i.getEndereco(),
                            i.getPreco(),
                            i.getStatus()
                    ))
                    .toList();
        }

        return service.listarPublicos().stream()
                .map(i -> new ImovelResponse(
                        i.getId(),
                        i.getTitulo(),
                        i.getEndereco(),
                        i.getPreco(),
                        i.getStatus()
                ))
                .toList();
    }

    @PutMapping("/{id}")
    public ImovelResponse atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ImovelRequest req) {

        var atualizado = service.atualizar(id, req);

        return new ImovelResponse(
                atualizado.getId(),
                atualizado.getTitulo(),
                atualizado.getEndereco(),
                atualizado.getPreco(),
                atualizado.getStatus()
        );
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}