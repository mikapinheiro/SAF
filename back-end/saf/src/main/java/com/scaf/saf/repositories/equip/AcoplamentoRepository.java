package com.scaf.saf.repositories.equip;

import com.scaf.saf.entities.equip.Acoplamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 08Mobile
 */
@Repository
@RepositoryRestResource(path = "acoplamentos")
public interface AcoplamentoRepository extends JpaRepository<Acoplamento, Long>{
    
}
