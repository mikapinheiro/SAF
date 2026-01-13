package com.scaf.saf.entities.falhas;

// Imports de entidades de OUTROS pacotes
import com.scaf.saf.entities.equip.Equipamento;
import com.scaf.saf.entities.falhas.Fabricante;
import com.scaf.saf.entities.falhas.Ativo;
import com.scaf.saf.entities.falhas.ClasseEquipamento;
import com.scaf.saf.entities.falhas.Sistema;
import com.scaf.saf.entities.falhas.Plataforma; 
import com.scaf.saf.entities.usuario.Usuario;

// Imports das entidades deste pacote
import com.scaf.saf.entities.falhas.StatusFalha;
import com.scaf.saf.entities.falhas.CausaFalha;
import com.scaf.saf.entities.falhas.SubdivisaoCausaFalha;
import com.scaf.saf.entities.falhas.Mecanismo;
import com.scaf.saf.entities.falhas.SubMecanismo;
import com.scaf.saf.entities.falhas.MetodoDetectacao;
import com.scaf.saf.entities.falhas.OrigemFalha;

// Imports do Jakarta (JPA)
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
@Table(name = "tb_registro_falha")
public class Falhas {
      
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_falha")
    private Long id;
    
    @Column(name = "RTA")
    private String rta;
    
    @Column(name = "data_ocorrencia")
    private LocalDate data;
    
    @Column(name = "descricao_falha")
    private String descricao;
    
    // --- RELACIONAMENTOS (com outras ENTIDADES) ---
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_equipamento_falho") 
    private Equipamento equipamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_usuario_registro") 
    private Usuario usuario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_plataforma")
    private Plataforma plataforma;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_sistema")
    private Sistema sistema;    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_fabricante")
    private Fabricante fabricante;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_classe_equipamento")
    private ClasseEquipamento classeEquipamento;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_mecanismo_falha")
    private Mecanismo mecanismo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_submecanismo_falha")
    private SubMecanismo submecanismo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_causa_falha")
    private CausaFalha causafalha;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_subdivisao_causafalha")
    private SubdivisaoCausaFalha subdivisaoCausaFalha;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_metodo_detectacao")
    private MetodoDetectacao metododetectacao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_origem_falha")
    private OrigemFalha origemfalha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_ativo")
    private Ativo ativo;
    
    // ********* CORREÇÃO FINAL: Mapeamento para o nome da PK referenciada *********
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="id_status")
    private StatusFalha status;

    
    // --- CONSTRUTOR ---
    public Falhas() {
    }

    // --- GETTERS E SETTERS ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRta() {
        return rta;
    }

    public void setRta(String rta) {
        this.rta = rta;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public ClasseEquipamento getClasseEquipamento() {
        return classeEquipamento;
    }

    public void setClasseEquipamento(ClasseEquipamento classeEquipamento) {
        this.classeEquipamento = classeEquipamento;
    }

    public Mecanismo getMecanismo() {
        return mecanismo;
    }

    public void setMecanismo(Mecanismo mecanismo) {
        this.mecanismo = mecanismo;
    }

    public SubMecanismo getSubmecanismo() {
        return submecanismo;
    }

    public void setSubmecanismo(SubMecanismo submecanismo) {
        this.submecanismo = submecanismo;
    }

    public CausaFalha getCausafalha() {
        return causafalha;
    }

    public void setCausafalha(CausaFalha causafalha) {
        this.causafalha = causafalha;
    }

    public SubdivisaoCausaFalha getSubdivisaoCausaFalha() {
        return subdivisaoCausaFalha;
    }

    public void setSubdivisaoCausaFalha(SubdivisaoCausaFalha subdivisaoCausaFalha) {
        this.subdivisaoCausaFalha = subdivisaoCausaFalha;
    }

    public MetodoDetectacao getMetododetectacao() {
        return metododetectacao;
    }

    public void setMetododetectacao(MetodoDetectacao metododetectacao) {
        this.metododetectacao = metododetectacao;
    }

    public OrigemFalha getOrigemfalha() {
        return origemfalha;
    }

    public void setOrigemfalha(OrigemFalha origemfalha) {
        this.origemfalha = origemfalha;
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public void setAtivo(Ativo ativo) {
        this.ativo = ativo;
    }

    public StatusFalha getStatus() {
        return status;
    }

    public void setStatus(StatusFalha status) {
        this.status = status;
    }
}