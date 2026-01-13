package com.scaf.saf.repositories.equip;

import com.scaf.saf.entities.equip.TipoCorpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 08Mobile
 */
@Repository
@RepositoryRestResource(path = "tipos-corpo")
public interface TipoCorpoRepository extends JpaRepository<TipoCorpo, Long>{
    
}
