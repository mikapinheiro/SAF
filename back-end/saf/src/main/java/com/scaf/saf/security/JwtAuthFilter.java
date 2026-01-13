package com.scaf.saf.security;

import com.scaf.saf.security.TokenService;
import com.scaf.saf.security.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    // Garante que o filtro seja ignorado para POST /api/auth/login e requisições OPTIONS
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String uri = request.getRequestURI();
        
        boolean isLoginPost = request.getMethod().equals("POST") && 
                              (uri.endsWith("/auth/login") || uri.endsWith("/auth/login/"));
                              
        boolean isOptionsRequest = request.getMethod().equals("OPTIONS");

        return isLoginPost || isOptionsRequest;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // A lógica do filtro só é executada se shouldNotFilter for FALSE
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            
            String token = authHeader.substring(7);

            if (tokenService.isTokenValido(token)) {
                
                String registroEmpregado = tokenService.getRegistroEmpregadoDoToken(token); 
                
                // CORREÇÃO: Adiciona verificação de nulo
                if (registroEmpregado != null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(registroEmpregado);

                    var authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        
        filterChain.doFilter(request, response);
    }
}