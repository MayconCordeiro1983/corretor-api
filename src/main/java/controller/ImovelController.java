package com.corretor.corretor.controller;

import com.corretor.corretor.dto.ImovelRequest;
import com.corretor.corretor.dto.ImovelResponse;
import com.corretor.corretor.service.ImovelService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ImovelResponse> criar(@RequestBody ImovelRequest req) {
        var salvo = service.criar(req);
        return ResponseEntity.ok(
                new ImovelResponse(salvo.getId(), salvo.getTitulo(), salvo.getEndereco(), salvo.getPreco())
        );
    }

    @GetMapping
    public ResponseEntity<List<ImovelResponse>> listarTodos() {
        var lista = service.listarMeus().stream()
                .map(i -> new ImovelResponse(i.getId(), i.getTitulo(), i.getEndereco(), i.getPreco()))
                .toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/meus")
    public ResponseEntity<List<ImovelResponse>> meus() {
        var lista = service.listarMeus().stream()
                .map(i -> new ImovelResponse(i.getId(), i.getTitulo(), i.getEndereco(), i.getPreco()))
                .toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ImovelResponse>> buscar(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Double precoMax) {

        List<ImovelResponse> lista;

        if (titulo != null && !titulo.isBlank()) {
            lista = service.buscarPorTitulo(titulo).stream()
                    .map(i -> new ImovelResponse(i.getId(), i.getTitulo(), i.getEndereco(), i.getPreco()))
                    .toList();
        } else if (precoMax != null) {
            lista = service.buscarPorPrecoMax(precoMax).stream()
                    .map(i -> new ImovelResponse(i.getId(), i.getTitulo(), i.getEndereco(), i.getPreco()))
                    .toList();
        } else {
            lista = service.listarMeus().stream()
                    .map(i -> new ImovelResponse(i.getId(), i.getTitulo(), i.getEndereco(), i.getPreco()))
                    .toList();
        }

        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImovelResponse> atualizar(@PathVariable Long id, @RequestBody ImovelRequest req) {
        var atualizado = service.atualizar(id, req);
        return ResponseEntity.ok(
                new ImovelResponse(atualizado.getId(), atualizado.getTitulo(), atualizado.getEndereco(), atualizado.getPreco())
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}