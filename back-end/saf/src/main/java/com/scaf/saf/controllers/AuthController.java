package com.scaf.saf.controllers;

import com.scaf.saf.dto.LoginRequestDTO; 
import com.scaf.saf.dto.LoginResponseDTO; 
import com.scaf.saf.entities.usuario.Usuario;
import com.scaf.saf.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO data) {
        
        // Usa o RE como username para o Spring Security
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getRe(), data.getSenha());
        
        // Tenta autenticar (usa o PasswordEncoder para comparar a senha)
        var auth = this.authenticationManager.authenticate(usernamePassword);
        
        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}