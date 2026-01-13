package com.scaf.saf.repositories.usuario;

import com.scaf.saf.entities.usuario.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
    
    // Buscar as últimas 4 não lidas por usuário, ordenadas pela mais recente
    List<Notificacao> findTop4ByDestinatarioIdAndLidaFalseOrderByDataCriacaoDesc(Long destinatarioId);
    
    // Buscar todas as notificações por usuário, ordenadas pela mais recente
    List<Notificacao> findByDestinatarioIdOrderByDataCriacaoDesc(Long destinatarioId);

    // Método para buscar notificações de rascunho
    List<Notificacao> findByDestinatarioIdAndTipoAlertaOrderByDataCriacaoDesc(Long destinatarioId, String tipoAlerta);
}