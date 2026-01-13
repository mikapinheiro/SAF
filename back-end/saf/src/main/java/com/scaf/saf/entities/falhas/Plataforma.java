package com.scaf.saf.entities.falhas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_plataforma")
public class Plataforma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plataforma")
    private Long id;

    @Column(name = "nome_plataforma")
    private String name;
    
    @Column(name = "sigla")
    private String sigla;

    public Plataforma() {}

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
}