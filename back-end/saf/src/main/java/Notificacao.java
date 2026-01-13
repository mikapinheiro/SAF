package com.scaf.saf.entities.usuario;

import com.scaf.saf.entities.falhas.Falhas;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_notificacao")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // O destinatário da notificação
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destinatario")
    private Usuario destinatario;

    @Column(name = "mensagem", nullable = false)
    private String mensagem;

    @Column(name = "tipo_alerta", nullable = false)
    private String tipoAlerta; // Ex: STATUS_ALTERADO, RASCUNHO_PENDENTE, NOVO_REGISTRO

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Column(name = "lida")
    private Boolean lida = false;
    
    // Opcional: Linka para a falha ou rascunho
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_referencia_falha")
    private Falhas falha;

    // Construtores, Getters e Setters
    public Notificacao() {}

    // Construtor útil para criar notificações
    public Notificacao(Usuario destinatario, String mensagem, String tipoAlerta, Falhas falha) {
        this.destinatario = destinatario;
        this.mensagem = mensagem;
        this.tipoAlerta = tipoAlerta;
        this.falha = falha;
    }
    
    // --- Getters e Setters (adicionar todos) ---
    // ...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Usuario getDestinatario() { return destinatario; }
    public void setDestinatario(Usuario destinatario) { this.destinatario = destinatario; }
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public String getTipoAlerta() { return tipoAlerta; }
    public void setTipoAlerta(String tipoAlerta) { this.tipoAlerta = tipoAlerta; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    public Boolean getLida() { return lida; }
    public void setLida(Boolean lida) { this.lida = lida; }
    public Falhas getFalha() { return falha; }
    public void setFalha(Falhas falha) { this.falha = falha; }
}