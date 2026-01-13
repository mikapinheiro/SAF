package com.scaf.saf.controllers;

import com.scaf.saf.repositories.falhas.FalhasRepository;
import com.scaf.saf.repositories.falhas.FalhasRepository.StatusCountProjection;
import com.scaf.saf.repositories.falhas.FalhasRepository.MonthlyCountProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dashboard") 
public class DashboardController {

    // Adicionado Autowired para o FalhasRepository, que tem os métodos de contagem
    @Autowired
    private FalhasRepository falhasRepository;

    // --- Mapeamento para /api/dashboard/estatisticas (ENDPOINT ESPERADO PELO FRONT) ---
    @GetMapping("/estatisticas")
    public ResponseEntity<Map<String, Long>> getEstatisticas() {
        // Usa o método criado no FalhasRepository
        List<StatusCountProjection> counts = falhasRepository.countFalhasByStatus();
        
        // 1. Processa a contagem de falhas por status
        Map<String, Long> metrics = counts.stream()
            .collect(Collectors.toMap(
                projection -> {
                    Long statusId = projection.getStatusId();
                    if (statusId == 1L) return "emAnalise";
                    if (statusId == 2L) return "aprovadas";
                    if (statusId == 3L) return "recusadas";
                    if (statusId == 4L) return "rascunhos";
                    return "outro"; 
                },
                StatusCountProjection::getCount
            ));

        // 2. Calcula o total de falhas
        long totalFalhas = counts.stream().mapToLong(StatusCountProjection::getCount).sum();
        metrics.put("totalFalhas", totalFalhas);

        return ResponseEntity.ok(metrics);
    }
    
    // --- Endpoint opcional para o Gráfico de Falhas por Mês ---
    @GetMapping("/grafico-mensal")
    public ResponseEntity<List<MonthlyCountProjection>> getGraficoMensal() {
        List<MonthlyCountProjection> monthlyData = falhasRepository.countFalhasByMonth();
        return ResponseEntity.ok(monthlyData);
    }
}