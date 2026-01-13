package com.scaf.saf.entities.falhas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_sistema") // <-- Esta é a correção
public class Sistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sistema") // <-- Correção da coluna
    private Long id;

    @Column(name = "nome_sistema") // <-- Correção da coluna
    private String name;
    
    @Column(name = "sigla_sistema") // <-- Correção da coluna
    private String sigla;

    // --- CONSTRUTOR ---
    public Sistema() {}

    // --- GETTERS E SETTERS ---
    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
    }
    
    public String getName() { 
        return name; 
    }
    
    public void setName(String name) { 
        this.name = name; 
    }
    
    public String getSigla() { 
        return sigla; 
    }
    
    public void setSigla(String sigla) { 
        this.sigla = sigla; 
    }

    public String getNome() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getDescricao() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}