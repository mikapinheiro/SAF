package com.scaf.saf.controllers;

import com.scaf.saf.dto.HistoricoAcaoDTO; // 1. IMPORTAR O DTO
import com.scaf.saf.entities.falhas.HistoricoAcao;
import com.scaf.saf.repositories.falhas.HistoricoAcaoRepository;
import java.util.List;
import java.util.stream.Collectors; // 2. IMPORTAR STREAMS
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") 
public class HistoricoAcaoController {

    @Autowired
    private HistoricoAcaoRepository historicoAcaoRepository;

    @GetMapping("/falhas/{falhaId}/historico")
    // 3. MUDAR O TIPO DE RETORNO para List<HistoricoAcaoDTO>
    public ResponseEntity<List<HistoricoAcaoDTO>> getHistoricoPorFalhaId(@PathVariable Long falhaId) {
        
        // 4. Busca as entidades completas do banco
        List<HistoricoAcao> historicoCompleto = historicoAcaoRepository.findByFalhaIdOrderByDataHoraAcaoAsc(falhaId);

        // 5. Converte a lista de entidades "sujas" para a lista de DTOs "limpos"
        List<HistoricoAcaoDTO> historicoLimpo = historicoCompleto.stream()
                .map(historico -> new HistoricoAcaoDTO(historico))
                .collect(Collectors.toList());
        
        // 6. Retorna o JSON limpo
        return ResponseEntity.ok(historicoLimpo);
    }
}