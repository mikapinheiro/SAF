package com.scaf.saf.dto;

// Este é o "formulário" que o front-end vai enviar
public class AnaliseRequestDTO {

    private Long newStatusId; // O ID do novo status (2 para Aprovada, 3 para Recusada)
    private Long usuarioAcaoId; // O ID do usuário (Admin/Gerente) que está fazendo a análise
    private String descricaoAcao; // A justificativa/observação da análise

    
    public Long getNewStatusId() {
        return newStatusId;
    }

    public void setNewStatusId(Long newStatusId) {
        this.newStatusId = newStatusId;
    }

    public Long getUsuarioAcaoId() {
        return usuarioAcaoId;
    }

    public void setUsuarioAcaoId(Long usuarioAcaoId) {
        this.usuarioAcaoId = usuarioAcaoId;
    }

    public String getDescricaoAcao() {
        return descricaoAcao;
    }

    public void setDescricaoAcao(String descricaoAcao) {
        this.descricaoAcao = descricaoAcao;
    }
}