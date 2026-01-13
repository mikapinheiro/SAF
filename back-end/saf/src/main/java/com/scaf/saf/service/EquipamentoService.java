package com.scaf.saf.service;

import com.scaf.saf.entities.equip.Equipamento;
import com.scaf.saf.repositories.equip.EquipamentoRepository; 
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils; // 1. IMPORTAR
import org.springframework.beans.BeanWrapper; // 2. IMPORTAR
import org.springframework.beans.BeanWrapperImpl; // 3. IMPORTAR
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRepository; 

    public List<Equipamento> findAll() {
        return equipamentoRepository.findAll();
    }

    public Optional<Equipamento> findById(Long id) {
        return equipamentoRepository.findById(id);
    }

    public Equipamento save(Equipamento equipamento) {
        return equipamentoRepository.save(equipamento);
    }
    
    // --- ADICIONE ESTE MÉTODO NOVO ---
    public Equipamento update(Long id, Equipamento equipamentoAtualizado) {
        // 1. Busca o equipamento existente no banco
        Equipamento equipamentoExistente = equipamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado: " + id));

        // 2. Copia os valores não-nulos do objeto atualizado para o existente
        // Isso impede que o front-end apague dados sem querer
        BeanUtils.copyProperties(equipamentoAtualizado, equipamentoExistente, getNullPropertyNames(equipamentoAtualizado));
        
        // 3. Garante que o ID não seja alterado
        equipamentoExistente.setId(id);

        // 4. Salva o objeto atualizado
        return equipamentoRepository.save(equipamentoExistente);
    }
    
    // Método auxiliar para copiar apenas propriedades não nulas
    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        java.util.Set<String> emptyNames = new java.util.HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
    // --- FIM DOS MÉTODOS NOVOS ---

    public void deleteById(Long id) {
        equipamentoRepository.deleteById(id);
    }
}