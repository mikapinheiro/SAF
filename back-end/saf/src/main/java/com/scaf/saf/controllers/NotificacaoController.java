package com.scaf.saf.controllers;

import com.scaf.saf.dto.NotificacaoDTO;
import com.scaf.saf.service.NotificacaoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.scaf.saf.entities.usuario.Usuario; // Assumindo que você pode fazer o cast

@RestController
@RequestMapping("/api/notificacoes")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    // Endpoint para listar as últimas 4 notificações não lidas
    @GetMapping("/ultimas")
    public ResponseEntity<List<NotificacaoDTO>> getUltimasNotificacoes(@AuthenticationPrincipal UserDetails userDetails) {
        // Cast para obter o ID do usuário logado (UserDetails é a sua entidade Usuario)
        Long userId = ((Usuario) userDetails).getId(); 
        
        List<NotificacaoDTO> notificacoes = notificacaoService.getUltimasNaoLidas(userId);
        return ResponseEntity.ok(notificacoes);
    }
    
    // Endpoint para marcar uma notificação como lida
    @PatchMapping("/{id}/lida")
    public ResponseEntity<Void> marcarComoLida(@PathVariable Long id) {
        notificacaoService.marcarComoLida(id);
        return ResponseEntity.noContent().build();
    }
    
    // Endpoint para marcar todas como lidas
    @PatchMapping("/limpar")
    public ResponseEntity<Void> limparNotificacoes(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId = ((Usuario) userDetails).getId();
        notificacaoService.marcarTodasComoLidas(userId);
        return ResponseEntity.noContent().build();
    }
}