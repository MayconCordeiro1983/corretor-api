package com.corretor.corretor.relatorio;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/relatorios")
@CrossOrigin("*")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/imoveis/pdf")
    public ResponseEntity<byte[]> gerarRelatorioImoveisPdf(
            @RequestParam(required = false) String titulo,
            @RequestParam(defaultValue = "titulo") String ordenarPor) {

        byte[] pdf = relatorioService.gerarRelatorioImoveis(titulo, ordenarPor);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=relatorio-imoveis.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}