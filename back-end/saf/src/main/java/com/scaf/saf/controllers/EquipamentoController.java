package com.scaf.saf.controllers;

import com.scaf.saf.entities.equip.Equipamento;
import com.scaf.saf.service.EquipamentoService; 
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
@RequestMapping("/api/equipamentos") 
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService; 

    @PostMapping
    public Equipamento createEquipamento(@RequestBody Equipamento equipamento) {
        return equipamentoService.save(equipamento);
    }

    @GetMapping
    public List<Equipamento> getAllEquipamentos() {
        return equipamentoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> getEquipamentoById(@PathVariable Long id) {
        return equipamentoService.findById(id)
                .map(equipamentoEncontrado -> ResponseEntity.ok(equipamentoEncontrado))
                .orElse(ResponseEntity.notFound().build());
    }
    
    // --- ADICIONE ESTE MÉTODO NOVO ---
    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> updateEquipamento(
            @PathVariable Long id, 
            @RequestBody Equipamento equipamentoDetalhes) {
        
        Equipamento equipamentoAtualizado = equipamentoService.update(id, equipamentoDetalhes);
        return ResponseEntity.ok(equipamentoAtualizado);
    }
    // --- FIM DO MÉTODO NOVO ---

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipamento(@PathVariable Long id) {
        equipamentoService.deleteById(id);
        return ResponseEntity.noContent().build(); 
    }
}