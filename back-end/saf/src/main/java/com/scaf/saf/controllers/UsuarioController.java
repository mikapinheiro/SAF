package com.scaf.saf.controllers;

import com.scaf.saf.entities.usuario.Usuario;
import com.scaf.saf.service.UsuarioService; 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping; // 1. IMPORTAR
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios") 
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        return usuarioService.findById(id)
                .map(usuarioEncontrado -> ResponseEntity.ok(usuarioEncontrado))
                .orElse(ResponseEntity.notFound().build()); 
    }
    
    // --- ADICIONE ESTE MÉTODO NOVO ---
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(
            @PathVariable Long id, 
            @RequestBody Usuario usuarioDetalhes) {
        
        Usuario usuarioAtualizado = usuarioService.update(id, usuarioDetalhes);
        return ResponseEntity.ok(usuarioAtualizado);
    }
    // --- FIM DO MÉTODO NOVO ---

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build(); 
    }
}