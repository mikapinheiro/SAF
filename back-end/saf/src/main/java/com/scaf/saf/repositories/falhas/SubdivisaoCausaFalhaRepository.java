package com.scaf.saf.repositories.falhas;

import com.scaf.saf.entities.falhas.SubdivisaoCausaFalha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 08Mobile
 */
@Repository
@RepositoryRestResource(path = "subdivisoes-causa-falha")
public interface SubdivisaoCausaFalhaRepository extends JpaRepository<SubdivisaoCausaFalha, Long> {
    
}
