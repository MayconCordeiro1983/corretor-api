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
                return toResponse(salvo);
        }

        @GetMapping("/meus")
        public List<ImovelResponse> meus() {
                return service.listarMeus()
                                .stream()
                                .map(this::toResponse)
                                .toList();
        }

        @GetMapping("/buscar")
        public List<ImovelResponse> buscar(
                        @RequestParam(required = false) String titulo,
                        @RequestParam(required = false) Double precoMax) {

                if (titulo != null && !titulo.isBlank()) {
                        return service.buscarPorTitulo(titulo)
                                        .stream()
                                        .map(this::toResponse)
                                        .toList();
                }

                if (precoMax != null) {
                        return service.buscarPorPrecoMax(precoMax)
                                        .stream()
                                        .map(this::toResponse)
                                        .toList();
                }

                return service.listarMeus()
                                .stream()
                                .map(this::toResponse)
                                .toList();
        }

        @GetMapping("/publicos")
        public List<ImovelResponse> publicos() {
                return service.listarPublicos()
                                .stream()
                                .map(this::toResponse)
                                .toList();
        }

        @GetMapping("/publicos/buscar")
        public List<ImovelResponse> buscarPublicos(
                        @RequestParam(required = false) String titulo,
                        @RequestParam(required = false) Double precoMax) {

                if (titulo != null && !titulo.isBlank()) {
                        return service.buscarPublicoPorTitulo(titulo)
                                        .stream()
                                        .map(this::toResponse)
                                        .toList();
                }

                if (precoMax != null) {
                        return service.buscarPublicoPorPrecoMax(precoMax)
                                        .stream()
                                        .map(this::toResponse)
                                        .toList();
                }

                return service.listarPublicos()
                                .stream()
                                .map(this::toResponse)
                                .toList();
        }

        @PutMapping("/{id}")
        public ImovelResponse atualizar(
                        @PathVariable Long id,
                        @Valid @RequestBody ImovelRequest req) {

                var atualizado = service.atualizar(id, req);
                return toResponse(atualizado);
        }

        @DeleteMapping("/{id}")
        public void deletar(@PathVariable Long id) {
                service.deletar(id);
        }

        private ImovelResponse toResponse(com.corretor.corretor.model.Imovel i) {
                return new ImovelResponse(
                                i.getId(),
                                i.getTitulo(),
                                i.getEndereco(),
                                i.getPreco(),
                                i.getBairro(),
                                i.getCidade(),
                                i.getEstado(),
                                i.getCep(),
                                i.getMapaUrl(),
                                i.getStatus(),
                                i.getImagemUrl());
        }
}