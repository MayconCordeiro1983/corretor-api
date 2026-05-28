package com.corretor.corretor.relatorio;

import com.corretor.corretor.model.Imovel;
import com.corretor.corretor.model.Proposta;
import com.corretor.corretor.repository.ImovelRepository;
import com.corretor.corretor.repository.PropostaRepository;
import com.corretor.corretor.service.UsuarioService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RelatorioService {

    private final ImovelRepository imovelRepository;
    private final UsuarioService usuarioService;
    private final PropostaRepository propostaRepository;

    public RelatorioService(
            ImovelRepository imovelRepository,
            UsuarioService usuarioService,
            PropostaRepository propostaRepository) {
        this.imovelRepository = imovelRepository;
        this.usuarioService = usuarioService;
        this.propostaRepository = propostaRepository;
    }

    public byte[] gerarRelatorioImoveis(String titulo, String ordenarPor) {
        try {
            String email = usuarioService.getEmailLogado();

            List<Imovel> imoveis = imovelRepository.findByUsuarioEmail(email);

            if (titulo != null && !titulo.isBlank()) {
                imoveis = imoveis.stream()
                        .filter(i -> i.getTitulo() != null &&
                                i.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                        .toList();
            }

            if ("preco".equalsIgnoreCase(ordenarPor)) {
                imoveis = imoveis.stream()
                        .sorted(Comparator.comparing(Imovel::getPreco, Comparator.nullsLast(Double::compareTo)))
                        .toList();
            } else {
                imoveis = imoveis.stream()
                        .sorted(Comparator.comparing(Imovel::getTitulo,
                                Comparator.nullsLast(String::compareToIgnoreCase)))
                        .toList();
            }

            InputStream arquivo = getClass().getResourceAsStream("/relatorios/imoveis.jrxml");

            if (arquivo == null) {
                throw new RuntimeException("Arquivo imoveis.jrxml não encontrado");
            }

            double valorTotal = imoveis.stream()
                    .filter(i -> i.getPreco() != null)
                    .mapToDouble(Imovel::getPreco)
                    .sum();

            double mediaPrecos = imoveis.stream()
                    .filter(i -> i.getPreco() != null)
                    .mapToDouble(Imovel::getPreco)
                    .average()
                    .orElse(0.0);

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("DATA_GERACAO", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            parametros.put("TOTAL_IMOVEIS", imoveis.size());
            parametros.put("VALOR_TOTAL", valorTotal);
            parametros.put("MEDIA_PRECOS", mediaPrecos);
            parametros.put("FILTRO",
                    titulo == null || titulo.isBlank() ? "Todos os imóveis" : "Título contendo: " + titulo);
            parametros.put("ORDENACAO", "preco".equalsIgnoreCase(ordenarPor) ? "Preço" : "Título");

            JasperReport jasperReport = JasperCompileManager.compileReport(arquivo);
            JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource(imoveis);

            JasperPrint print = JasperFillManager.fillReport(jasperReport, parametros, dados);

            ByteArrayOutputStream pdf = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(print, pdf);

            return pdf.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro gerar PDF: " + e.getMessage());
        }
    }

    public byte[] gerarRelatorioPropostas() {
        try {
            String email = usuarioService.getEmailLogado();

            List<Proposta> propostas = propostaRepository.findByImovelUsuarioEmail(email);

            propostas = propostas.stream()
                    .sorted(Comparator
                            .comparing(Proposta::getDataCriacao, Comparator.nullsLast(LocalDateTime::compareTo))
                            .reversed())
                    .toList();

            InputStream arquivo = getClass().getResourceAsStream("/relatorios/propostas.jrxml");

            if (arquivo == null) {
                throw new RuntimeException("Arquivo propostas.jrxml não encontrado");
            }

            double valorTotal = propostas.stream()
                    .filter(p -> p.getValorProposto() != null)
                    .mapToDouble(Proposta::getValorProposto)
                    .sum();

            double mediaPropostas = propostas.stream()
                    .filter(p -> p.getValorProposto() != null)
                    .mapToDouble(Proposta::getValorProposto)
                    .average()
                    .orElse(0.0);

            long totalEmAnalise = propostas.stream()
                    .filter(p -> p.getStatus() != null && p.getStatus().name().equals("EM_ANALISE"))
                    .count();

            long totalAceitas = propostas.stream()
                    .filter(p -> p.getStatus() != null && p.getStatus().name().equals("ACEITA"))
                    .count();

            long totalRecusadas = propostas.stream()
                    .filter(p -> p.getStatus() != null && p.getStatus().name().equals("RECUSADA"))
                    .count();

            Map<String, Object> parametros = new HashMap<>();

            parametros.put(
                    "DATA_GERACAO",
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

            parametros.put("TOTAL_PROPOSTAS", propostas.size());
            parametros.put("VALOR_TOTAL", valorTotal);
            parametros.put("MEDIA_PROPOSTAS", mediaPropostas);
            parametros.put("TOTAL_EM_ANALISE", totalEmAnalise);
            parametros.put("TOTAL_ACEITAS", totalAceitas);
            parametros.put("TOTAL_RECUSADAS", totalRecusadas);

            JasperReport jasperReport = JasperCompileManager.compileReport(arquivo);

            JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource(propostas);

            JasperPrint print = JasperFillManager.fillReport(jasperReport, parametros, dados);

            ByteArrayOutputStream pdf = new ByteArrayOutputStream();

            JasperExportManager.exportReportToPdfStream(print, pdf);

            return pdf.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro gerar PDF propostas: " + e.getMessage());
        }
    }
}