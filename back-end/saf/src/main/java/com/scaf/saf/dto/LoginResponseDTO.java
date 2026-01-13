package com.scaf.saf.dto;

public class LoginResponseDTO {
    private String token;

    // Construtor
    public LoginResponseDTO(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}