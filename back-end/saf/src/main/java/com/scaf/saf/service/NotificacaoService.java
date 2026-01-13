package com.scaf.saf.service;

import com.scaf.saf.dto.NotificacaoDTO;
import com.scaf.saf.entities.falhas.Falhas;
import com.scaf.saf.entities.usuario.Notificacao;
import com.scaf.saf.entities.usuario.Usuario;
import com.scaf.saf.repositories.usuario.NotificacaoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    // Função 1: Criar/Disparar uma nova notificação
    public Notificacao criarNotificacao(Usuario destinatario, String mensagem, String tipoAlerta, Falhas falha) {
        Notificacao notif = new Notificacao(destinatario, mensagem, tipoAlerta, falha);
        return notificacaoRepository.save(notif);
    }

    // Função 2: Converter Entidade para DTO
    private NotificacaoDTO toDTO(Notificacao notif) {
        // Implementar a lógica de conversão aqui
        // Ex: return new NotificacaoDTO(notif.getId(), notif.getMensagem(), ...);
        // NOTA: É fundamental mapear todos os campos que você quer que o front-end veja.
        return null; 
    }

    // Função 3: Listar as últimas 4 não lidas para o pop-up
    public List<NotificacaoDTO> getUltimasNaoLidas(Long destinatarioId) {
        List<Notificacao> notificacoes = notificacaoRepository.findTop4ByDestinatarioIdAndLidaFalseOrderByDataCriacaoDesc(destinatarioId);
        return notificacoes.stream().map(this::toDTO).collect(Collectors.toList());
    }
    
    // Função 4: Marcar como lida
    public void marcarComoLida(Long notificacaoId) {
        notificacaoRepository.findById(notificacaoId).ifPresent(notif -> {
            notif.setLida(true);
            notificacaoRepository.save(notif);
        });
    }
    
    // Função 5: Limpar todas as notificações (apenas marcar como lidas)
    public void marcarTodasComoLidas(Long destinatarioId) {
        notificacaoRepository.findByDestinatarioIdOrderByDataCriacaoDesc(destinatarioId).forEach(notif -> {
            if (!notif.getLida()) {
                notif.setLida(true);
                notificacaoRepository.save(notif);
            }
        });
    }
}