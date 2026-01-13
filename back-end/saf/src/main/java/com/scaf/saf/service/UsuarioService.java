package com.scaf.saf.service;

import com.scaf.saf.entities.usuario.Usuario;
import com.scaf.saf.repositories.usuario.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired 
    private PasswordEncoder passwordEncoder; 

    // 1. CREATE (Salva e Criptografa) - JÁ EXISTIA
    public Usuario save(Usuario usuario) {
        String senhaNaoCriptografada = usuario.getSenhaTemporaria();
        
        if (senhaNaoCriptografada != null && !senhaNaoCriptografada.trim().isEmpty()) {
            String senhaCriptografada = passwordEncoder.encode(senhaNaoCriptografada);
            usuario.setSenhaTemporaria(senhaCriptografada);
        }
        return usuarioRepository.save(usuario);
    }
    
    // 2. READ ALL (Listar todos) - NOVO MÉTODO (Linha 31 do Controller)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
    
    // 3. READ BY ID (Buscar por ID) - NOVO MÉTODO (Linha 36 do Controller)
    public Optional<Usuario> findById(Long id) {
        // Como o Repositório findAll/findById retorna a entidade Usuario,
        // garantimos que o Controller receba o tipo correto.
        return usuarioRepository.findById(id); 
    }
    
    // 4. UPDATE (Atualizar) - NOVO MÉTODO (Linha 47 do Controller)
    public Usuario update(Long id, Usuario usuarioDetalhes) {
        return usuarioRepository.findById(id).map(usuarioExistente -> {
            
            // Opcional: Atualizar campos aqui (ajustar conforme a necessidade real do seu sistema)
            usuarioExistente.setNome(usuarioDetalhes.getNome());
            usuarioExistente.setEmailCoorporativo(usuarioDetalhes.getEmailCoorporativo());
            // TODO: Adicionar lógica para TipoUsuario, SetorAtuacao, Plataforma, etc.
            
            // Se uma nova senha for fornecida, criptografe e atualize
            String novaSenha = usuarioDetalhes.getSenhaTemporaria();
            if (novaSenha != null && !novaSenha.trim().isEmpty()) {
                usuarioExistente.setSenhaTemporaria(passwordEncoder.encode(novaSenha));
            }
            
            return usuarioRepository.save(usuarioExistente);
        }).orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
    }

    // 5. DELETE (Excluir por ID) - NOVO MÉTODO (Linha 54 do Controller)
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}