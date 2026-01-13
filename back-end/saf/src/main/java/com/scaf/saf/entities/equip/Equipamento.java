package com.scaf.saf.entities.equip;

// Imports das entidades deste pacote
import com.scaf.saf.entities.equip.Acoplamento;
import com.scaf.saf.entities.equip.CategoriaEquipamento;
import com.scaf.saf.entities.equip.Instalacao;
import com.scaf.saf.entities.equip.Planta;
import com.scaf.saf.entities.equip.Posicao;
import com.scaf.saf.entities.equip.Selagem;
import com.scaf.saf.entities.equip.TipoCorpo;
import com.scaf.saf.entities.equip.TipoMancal;
import com.scaf.saf.entities.equip.TipoTransmissao;

// Imports das entidades do pacote 'falhas'
import com.scaf.saf.entities.falhas.Plataforma;
import com.scaf.saf.entities.falhas.Sistema;
import com.scaf.saf.entities.falhas.ClasseEquipamento;

// Imports do Jakarta
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "tb_registro_equipamento")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipamento")
    private Long id;

    @Column(name = "tag_equipamento")
    private String tagEquipamento;

    @Column(name = "descricao_equipamento")
    private String descricaoEquipamento;
    
    @Column(name = "numero_serie")
    private String numeroSerie;

    @Column(name = "data_instalacao")
    private LocalDate dataInstalacao;
    
    @Column(name = "numero_equipamento_pfceo")
    private String numeroEquipamentoPfceo;
    
    @Column(name = "numero_equipamento_sap")
    private String numeroEquipamentoSap;
    
    @Column(name = "centro_planejamento")
    private String centroPlanejamento;
    
    @Column(name = "local_instalacao_sap")
    private String localInstalacaoSap;

    @Column(name = "fluido_bombeado")
    private String fluidoBombeado;

    @Column(name = "tipo_servico")
    private String tipoServico;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "ano_fabricacao")
    private Integer anoFabricacao;

    @Column(name = "vazao")
    private String vazao;

    @Column(name = "potencia")
    private String potencia;

    @Column(name = "head")
    private String head;

    @Column(name = "npsha")
    private String npsha;

    @Column(name = "pressao_succao")
    private String pressaoSuccao;

    @Column(name = "pressao_descarga")
    private String pressaoDescarga;

    @Column(name = "diferenca_pressao")
    private String diferencaPressao;

    @Column(name = "norma_fabricacao")
    private String normaFabricacao;
    
    // --- Relacionamentos ---
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plataforma")
    private Plataforma plataforma; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sistema")
    private Sistema sistema; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_instalacao")
    private Instalacao instalacao; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_planta")
    private Planta planta; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private CategoriaEquipamento categoria; // Campo correto!
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_classe_equipamento")
    private ClasseEquipamento classeEquipamento; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_selagem")
    private Selagem selagem; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_corpo")
    private TipoCorpo tipoCorpo; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_acoplamento")
    private Acoplamento acoplamento; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_mancal")
    private TipoMancal tipoMancal; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_posicao")
    private Posicao posicao; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_transmissao")
    private TipoTransmissao tipoTransmissao; 
    
    
    // --- CONSTRUTOR ---
    public Equipamento() {}

    // --- GETTERS E SETTERS ---
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    // Getter para a TAG (Usado na Seção 2)
    public String getTagEquipamento() {
        return tagEquipamento;
    }

    public void setTagEquipamento(String tagEquipamento) {
        this.tagEquipamento = tagEquipamento;
    }

    public String getDescricaoEquipamento() {
        return descricaoEquipamento;
    }

    public void setDescricaoEquipamento(String descricaoEquipamento) {
        this.descricaoEquipamento = descricaoEquipamento;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public LocalDate getDataInstalacao() {
        return dataInstalacao;
    }

    public void setDataInstalacao(LocalDate dataInstalacao) {
        this.dataInstalacao = dataInstalacao;
    }

    public String getNumeroEquipamentoPfceo() {
        return numeroEquipamentoPfceo;
    }

    public void setNumeroEquipamentoPfceo(String numeroEquipamentoPfceo) {
        this.numeroEquipamentoPfceo = numeroEquipamentoPfceo;
    }

    public String getNumeroEquipamentoSap() {
        return numeroEquipamentoSap;
    }

    public void setNumeroEquipamentoSap(String numeroEquipamentoSap) {
        this.numeroEquipamentoSap = numeroEquipamentoSap;
    }

    public String getCentroPlanejamento() {
        return centroPlanejamento;
    }

    public void setCentroPlanejamento(String centroPlanejamento) {
        this.centroPlanejamento = centroPlanejamento;
    }

    public String getLocalInstalacaoSap() {
        return localInstalacaoSap;
    }

    public void setLocalInstalacaoSap(String localInstalacaoSap) {
        this.localInstalacaoSap = localInstalacaoSap;
    }

    public String getFluidoBombeado() {
        return fluidoBombeado;
    }

    public void setFluidoBombeado(String fluidoBombeado) {
        this.fluidoBombeado = fluidoBombeado;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getVazao() {
        return vazao;
    }

    public void setVazao(String vazao) {
        this.vazao = vazao;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getNpsha() {
        return npsha;
    }

    public void setNpsha(String npsha) {
        this.npsha = npsha;
    }

    public String getPressaoSuccao() {
        return pressaoSuccao;
    }

    public void setPressaoSuccao(String pressaoSuccao) {
        this.pressaoSuccao = pressaoSuccao;
    }

    public String getPressaoDescarga() {
        return pressaoDescarga;
    }

    public void setPressaoDescarga(String pressaoDescarga) {
        this.pressaoDescarga = pressaoDescarga;
    }

    public String getDiferencaPressao() {
        return diferencaPressao;
    }

    public void setDiferencaPressao(String diferencaPressao) {
        this.diferencaPressao = diferencaPressao;
    }

    public String getNormaFabricacao() {
        return normaFabricacao;
    }

    public void setNormaFabricacao(String normaFabricacao) {
        this.normaFabricacao = normaFabricacao;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    public Instalacao getInstalacao() {
        return instalacao;
    }

    public void setInstalacao(Instalacao instalacao) {
        this.instalacao = instalacao;
    }

    public Planta getPlanta() {
        return planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }

    // GETTER CORRETO: Categoria
    public CategoriaEquipamento getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEquipamento categoria) {
        this.categoria = categoria;
    }

    public ClasseEquipamento getClasseEquipamento() {
        return classeEquipamento;
    }

    public void setClasseEquipamento(ClasseEquipamento classeEquipamento) {
        this.classeEquipamento = classeEquipamento;
    }

    public Selagem getSelagem() {
        return selagem;
    }

    public void setSelagem(Selagem selagem) {
        this.selagem = selagem;
    }

    public TipoCorpo getTipoCorpo() {
        return tipoCorpo;
    }

    public void setTipoCorpo(TipoCorpo tipoCorpo) {
        this.tipoCorpo = tipoCorpo;
    }

    public Acoplamento getAcoplamento() {
        return acoplamento;
    }

    public void setAcoplamento(Acoplamento acoplamento) {
        this.acoplamento = acoplamento;
    }

    public TipoMancal getTipoMancal() {
        return tipoMancal;
    }

    public void setTipoMancal(TipoMancal tipoMancal) {
        this.tipoMancal = tipoMancal;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public TipoTransmissao getTipoTransmissao() {
        return tipoTransmissao;
    }

    public void setTipoTransmissao(TipoTransmissao tipoTransmissao) {
        this.tipoTransmissao = tipoTransmissao;
    }
    
    // Métodos para compatibilidade (TAG/Nome da Entidade)
    public String getNome() {
        return this.tagEquipamento; // Usamos a TAG como nome principal
    }
    
    // Métodos para compatibilidade (Descrição da Falha)
    public String getDescricao() {
        return this.descricaoEquipamento; 
    }
}