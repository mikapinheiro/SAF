package com.scaf.saf.repositories.equip;

import com.scaf.saf.entities.equip.TipoMancal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 08Mobile
 */
@Repository
public interface TipoMancalRepository extends JpaRepository<TipoMancal, Long>{
    
}
