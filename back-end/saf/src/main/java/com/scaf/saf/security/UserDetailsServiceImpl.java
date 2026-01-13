package com.scaf.saf.security;

import com.scaf.saf.repositories.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service 
public class UserDetailsServiceImpl implements UserDetailsService { 

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
        // CORREÇÃO: loadUserByUsername recebe o 'username' (que é o RE/Matrícula do Front-end)
        // O Front-end envia o RE no campo 'username'.
        UserDetails usuario = usuarioRepository.findByMatricula(matricula)
                .orElseThrow(() -> 
                        new UsernameNotFoundException("Usuário não encontrado com a Matrícula (RE): " + matricula)
                );
        
        // Retorna a Entidade Usuario, que é implicitamente convertida para UserDetails pelo Spring Security
        return usuario;
    }
}