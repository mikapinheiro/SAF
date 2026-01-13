package com.scaf.saf.dto;

import java.time.LocalDateTime;

public class NotificacaoDTO {
    private Long id;
    private String mensagem;
    private String tipoAlerta;
    private LocalDateTime dataCriacao;
    private Boolean lida;
    private Long falhaId; // O ID da falha para navegação

    // Construtor completo e Getters (para serialização)
    // ...
}