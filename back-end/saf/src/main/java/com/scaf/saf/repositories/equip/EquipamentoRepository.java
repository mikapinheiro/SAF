package com.scaf.saf.repositories.equip;

import com.scaf.saf.entities.equip.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 08Mobile
 */
@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long>{
    
}
