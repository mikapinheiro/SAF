package com.scaf.saf.security;

import com.scaf.saf.entities.usuario.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    // Recomenda-se obter isso do application.properties ou de uma variável de ambiente,
    // mas para manter a simplicidade do TCC, deixaremos aqui.
    private final String CHAVE_SECRETA = "MinhaChaveSecretaSuperLongaParaProtegerMeuTCCDoSAF2025";
    private final long EXPIRACAO_EM_HORAS = 8L;

    // Método auxiliar para pegar a chave
    private SecretKey getChaveDeAssinatura() {
        // Usa a chave secreta para gerar a SecretKey HmacSha256
        return Keys.hmacShaKeyFor(CHAVE_SECRETA.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Gera o Token JWT para o usuário logado.
     * O Subject é definido como o RE/Matrícula do usuário.
     */
    public String gerarToken(Usuario usuario) {
        
        Instant agora = Instant.now();
        Instant expiracao = agora.plus(EXPIRACAO_EM_HORAS, ChronoUnit.HOURS);
        
        SecretKey key = getChaveDeAssinatura();

        return Jwts.builder()
                .setIssuer("API-SAF") // Emissor
                .setSubject(usuario.getUsername()) // RE/Matrícula (definido no getUsername() do Usuario)
                .claim("nome", usuario.getNome()) // Informação extra, opcional
                .claim("role", usuario.getAuthorities().iterator().next().getAuthority()) // Perfil do usuário
                .setIssuedAt(Date.from(agora)) // Data de emissão
                .setExpiration(Date.from(expiracao)) // Data de expiração
                .signWith(key)
                .compact();
    }
    
    /**
     * Verifica se o token está assinado corretamente e se não expirou.
     */
    public boolean isTokenValido(String token) {
        try {
            Jwts.parser()
                .verifyWith(getChaveDeAssinatura())
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            // Se o token for inválido (expirado, modificado, etc.), o parser lança uma exceção.
            return false; 
        }
    }

    /**
     * Extrai o Subject do token (que é o RE/Matrícula) para carregar o usuário no filtro.
     */
    public String getRegistroEmpregadoDoToken(String token) {
        try {
             return Jwts.parser()
                    .verifyWith(getChaveDeAssinatura())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject(); // Retorna o RE/Matrícula
        } catch (Exception e) {
            // Em caso de erro (token inválido), retorna nulo ou uma string vazia
            return null; 
        }
    }
}