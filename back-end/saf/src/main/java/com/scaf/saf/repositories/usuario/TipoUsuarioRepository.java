package com.scaf.saf.repositories.usuario;

import com.scaf.saf.entities.usuario.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource; 
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "tipos-usuario") 
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {
    
}