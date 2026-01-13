package com.scaf.saf.dto;

public class DashboardStatusDTO {

    private long totalFalhas;
    private long emAnalise;
    private long aprovadas;
    private long recusadas;
    private long rascunhos;

    // Construtor padrão
    public DashboardStatusDTO() {
        this.totalFalhas = 0;
        this.emAnalise = 0;
        this.aprovadas = 0;
        this.recusadas = 0;
        this.rascunhos = 0;
    }

    // --- Getters e Setters ---
    // (Clique com o botão direito > Insert Code... > Getter and Setter... > Selecionar tudo)
    
    public long getTotalFalhas() {
        return totalFalhas;
    }

    public void setTotalFalhas(long totalFalhas) {
        this.totalFalhas = totalFalhas;
    }

    public long getEmAnalise() {
        return emAnalise;
    }

    public void setEmAnalise(long emAnalise) {
        this.emAnalise = emAnalise;
    }

    public long getAprovadas() {
        return aprovadas;
    }

    public void setAprovadas(long aprovadas) {
        this.aprovadas = aprovadas;
    }

    public long getRecusadas() {
        return recusadas;
    }

    public void setRecusadas(long recusadas) {
        this.recusadas = recusadas;
    }

    public long getRascunhos() {
        return rascunhos;
    }

    public void setRascunhos(long rascunhos) {
        this.rascunhos = rascunhos;
    }
}