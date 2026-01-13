package com.scaf.saf.entities.falhas;

import com.scaf.saf.entities.usuario.Usuario; 
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_historico_acao")
public class HistoricoAcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_acao")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_falha")
    private Falhas falha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_acao")
    private Usuario usuario;

    @Column(name = "data_hora_acao")
    private LocalDateTime dataHoraAcao;

    @Column(name = "descricao_acao")
    private String descricaoAcao;

    public HistoricoAcao() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Falhas getFalha() {
        return falha;
    }

    public void setFalha(Falhas falha) {
        this.falha = falha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
}