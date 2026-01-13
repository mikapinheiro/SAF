package com.scaf.saf.service;

import com.scaf.saf.dto.DashboardStatusDTO;
import com.scaf.saf.dto.MonthlyCountDTO;
import com.scaf.saf.repositories.falhas.FalhasRepository;
import com.scaf.saf.repositories.falhas.FalhasRepository.StatusCountProjection;
import com.scaf.saf.repositories.falhas.FalhasRepository.MonthlyCountProjection; // Importar a nova projeção
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private FalhasRepository falhasRepository;

    public DashboardStatusDTO getStats() {
        // 1. Chama a query de contagem agrupada por Status
        List<StatusCountProjection> counts = falhasRepository.countFalhasByStatus();

        DashboardStatusDTO stats = new DashboardStatusDTO();
        long total = 0;

        // 2. Mapeia os resultados da query para o DTO
        for (StatusCountProjection result : counts) {
            long count = result.getCount();
            total += count;

            // Mapeamento baseado no ID do Status no banco (1=Em Análise, 2=Aprovada, 3=Recusada, 4=Rascunho)
            // É mais seguro usar o ID do que o nome da string, como você fez.
            if (result.getStatusId() == 1L) {
                stats.setEmAnalise(count);
            } else if (result.getStatusId() == 2L) {
                stats.setAprovadas(count);
            } else if (result.getStatusId() == 3L) {
                stats.setRecusadas(count);
            } else if (result.getStatusId() == 4L) {
                stats.setRascunhos(count);
            }
            // Ignora outros status que possam existir
        }

        // 3. Define o total
        stats.setTotalFalhas(total);
        return stats;
    }
    
    /**
     * Busca dados para o gráfico de Falhas por Mês nos últimos 9 meses.
     */
    public List<MonthlyCountDTO> getMonthlyFaults() {
        // Assume que a query countFalhasByMonth() já foi adicionada ao FalhasRepository
        List<MonthlyCountProjection> results = falhasRepository.countFalhasByMonth();
        List<MonthlyCountDTO> monthlyData = new ArrayList<>();
        
        for (MonthlyCountProjection result : results) {
            // Mapeia o resultado da Projeção para o DTO
            monthlyData.add(new MonthlyCountDTO(result.getMes(), result.getCount()));
        }
        
        return monthlyData;
    }
}