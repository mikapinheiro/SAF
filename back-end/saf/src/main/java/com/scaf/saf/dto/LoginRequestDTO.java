package com.scaf.saf.dto;

public class LoginRequestDTO {
    private String re; // MUDANÇA: de 'email' para 're'
    private String senha;

    // Getters e Setters atualizados
    public String getRe() {
        return re;
    }
    public void setRe(String re) {
        this.re = re;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}