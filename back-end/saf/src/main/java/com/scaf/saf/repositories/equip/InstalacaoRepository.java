package com.scaf.saf.repositories.equip;

import com.scaf.saf.entities.equip.Instalacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 08Mobile
 */
@Repository
@RepositoryRestResource(path = "instalacoes")
public interface InstalacaoRepository extends JpaRepository<Instalacao, Long>{
    
}
