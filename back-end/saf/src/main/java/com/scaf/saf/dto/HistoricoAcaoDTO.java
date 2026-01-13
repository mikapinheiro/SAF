package com.scaf.saf.dto;

import com.scaf.saf.entities.falhas.HistoricoAcao;
import java.time.LocalDateTime;

// Este é o nosso "JSON Limpo"
public class HistoricoAcaoDTO {

    private Long id;
    private LocalDateTime dataHoraAcao;
    private String descricaoAcao;
    private String nomeUsuario;
    private String tipoUsuario;

    // Construtor que converte a Entidade completa para o DTO limpo
    public HistoricoAcaoDTO(HistoricoAcao historico) {
        this.id = historico.getId();
        this.dataHoraAcao = historico.getDataHoraAcao();
        this.descricaoAcao = historico.getDescricaoAcao();
        
        // Pega os dados do usuário associado para evitar LazyLoading
        if (historico.getUsuario() != null) {
            this.nomeUsuario = historico.getUsuario().getNome();
            if (historico.getUsuario().getTipoUsuario() != null) {
                this.tipoUsuario = historico.getUsuario().getTipoUsuario().getName();
            } else {
                this.tipoUsuario = "N/A";
            }
        } else {
            this.nomeUsuario = "Usuário Desconhecido";
            this.tipoUsuario = "N/A";
        }
    }

    // --- Getters e Setters ---
    // (Clique com o botão direito > Insert Code... > Getter and Setter... > Selecionar tudo)
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHoraAcao() {
        return dataHoraAcao;
    }

    public void setDataHoraAcao(LocalDateTime dataHoraAcao) {
        this.dataHoraAcao = dataHoraAcao;
    }

    public String getDescricaoAcao() {
        return descricaoAcao;
    }

    public void setDescricaoAcao(String descricaoAcao) {
        this.descricaoAcao = descricaoAcao;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}