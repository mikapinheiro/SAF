package com.scaf.saf.repositories.equip;

import com.scaf.saf.entities.equip.CategoriaEquipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 08Mobile
 */
@Repository
@RepositoryRestResource(path = "categorias-equipamento")
public interface CategoriaEquipamentoRepository extends JpaRepository<CategoriaEquipamento, Long>{
    
}
