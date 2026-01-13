package com.scaf.saf.repositories.equip;

import com.scaf.saf.entities.equip.Planta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "plantas")
public interface PlantaRepository extends JpaRepository<Planta, Long> {
}