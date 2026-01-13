package com.scaf.saf.repositories.usuario;

import com.scaf.saf.entities.usuario.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    // --- (Interfaces de Projeção permanecem as mesmas) ---
    
    // CORREÇÃO CRÍTICA: Ajustamos o nome do atributo na query HQL para 'registroEmpregado'
    // (O nome do campo Java na sua Entidade Usuario)
    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.tipoUsuario WHERE u.registroEmpregado = :matricula")
    Optional<Usuario> findByMatricula(@Param("matricula") String matricula);
    
    // Você também pode ter o método findByRegistroEmpregado se ele existir na sua Entidade.
    
    Optional<Usuario> findByEmailCoorporativo(String email); 
}