package com.scaf.saf.repositories.falhas;

import com.scaf.saf.entities.falhas.HistoricoAcao;
import java.util.List; // IMPORTAR
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoAcaoRepository extends JpaRepository<HistoricoAcao, Long>{

    List<HistoricoAcao> findByFalhaIdOrderByDataHoraAcaoAsc(Long falhaId);
}