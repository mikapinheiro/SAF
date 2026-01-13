package com.scaf.saf.repositories.falhas;

import com.scaf.saf.entities.falhas.Falhas;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FalhasRepository extends JpaRepository<Falhas, Long> {

    // --- INTERFACE DE PROJEÇÃO PARA CARTÕES (STATUS) ---
    public interface StatusCountProjection {
        Long getStatusId(); 
        Long getCount();
    }

    // --- INTERFACE DE PROJEÇÃO PARA GRÁFICO (MENSAL) ---
    public interface MonthlyCountProjection {
        String getMes(); 
        Long getCount();
    }
    
    // (Seu método findByIdCompleto continua aqui...)
    @Query("SELECT f FROM Falhas f " +
               "LEFT JOIN FETCH f.equipamento " +
               "LEFT JOIN FETCH f.usuario " +
               "LEFT JOIN FETCH f.plataforma " +
               "LEFT JOIN FETCH f.sistema " +
               "LEFT JOIN FETCH f.fabricante " +
               "LEFT JOIN FETCH f.classeEquipamento " +
               "LEFT JOIN FETCH f.mecanismo " +
               "LEFT JOIN FETCH f.submecanismo " +
               "LEFT JOIN FETCH f.causafalha " +
               "LEFT JOIN FETCH f.subdivisaoCausaFalha " +
               "LEFT JOIN FETCH f.metododetectacao " +
               "LEFT JOIN FETCH f.origemfalha " +
               "LEFT JOIN FETCH f.ativo " +
               "LEFT JOIN FETCH f.status " +
               "WHERE f.id = :id")
    Optional<Falhas> findByIdCompleto(@Param("id") Long id);

    List<Falhas> findByStatusId(Long statusId);
    
    // --- MÉTODO DE CONTAGEM POR STATUS (JPQL) ---
    // Usando JPQL para que o Hibernate utilize o mapeamento da Entity (id_status)
    @Query("SELECT f.status.id as statusId, COUNT(f) as count FROM Falhas f GROUP BY f.status.id")
    List<StatusCountProjection> countFalhasByStatus();
    
    // --- MÉTODO DE CONTAGEM MENSAL (SQL NATIVO) ---
    @Query(value = "SELECT DATE_TRUNC('month', data_criacao) AS mes, COUNT(id_falha) AS count " +
                   "FROM tb_falhas WHERE data_criacao >= CURRENT_DATE - INTERVAL '9 month' " +
                   "GROUP BY 1 ORDER BY 1", nativeQuery = true)
    List<MonthlyCountProjection> countFalhasByMonth();
}