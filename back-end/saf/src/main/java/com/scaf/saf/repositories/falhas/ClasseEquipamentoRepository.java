package com.scaf.saf.repositories.falhas; // <-- Verifique se este é o pacote correto

import com.scaf.saf.entities.falhas.ClasseEquipamento; // 1. Importa a ENTIDADE
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "classes-equipamento")
public interface ClasseEquipamentoRepository extends JpaRepository<ClasseEquipamento, Long> {
    
}