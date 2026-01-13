package com.scaf.saf.controllers;

import com.scaf.saf.dto.AnaliseRequestDTO; 
import com.scaf.saf.entities.falhas.Falhas;
import com.scaf.saf.service.FalhasService; 
import java.util.List;
import java.util.Optional; // 1. IMPORTAR
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; // 2. IMPORTAR
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/falhas") 

public class FalhasController {

    @Autowired
    private FalhasService falhasService;
    
    @PostMapping
    public Falhas createFalha(@RequestBody Falhas falha) {
        return falhasService.save(falha);
    }
    // --- MÉTODO ATUALIZADO ---
    @GetMapping
    public List<Falhas> getAllFalhas(@RequestParam(required = false) Long status) {
        // Converte o 'status' (que pode ser null) para um Optional
        return falhasService.findAll(Optional.ofNullable(status));
    }
    // --- FIM DA ATUALIZAÇÃO ---

    @GetMapping("/{id}")
    public ResponseEntity<Falhas> getFalhaById(@PathVariable Long id) {

        return falhasService.findById(id)
                .map(falhaEncontrada -> ResponseEntity.ok(falhaEncontrada)) 
                .orElse(ResponseEntity.notFound().build()); 
    }    
    
    @PatchMapping("/{id}/status")
    public ResponseEntity<Falhas> analisarFalha(
            @PathVariable Long id, 
            @RequestBody AnaliseRequestDTO analiseRequest) {
        
        Falhas falhaAtualizada = falhasService.analisarFalha(id, analiseRequest);
        return ResponseEntity.ok(falhaAtualizada);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFalha(@PathVariable Long id) {
        falhasService.deleteById(id);

        return ResponseEntity.noContent().build(); 
    }
}