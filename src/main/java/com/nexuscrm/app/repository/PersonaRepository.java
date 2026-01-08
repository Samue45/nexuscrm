package com.nexuscrm.app.repository;

import com.nexuscrm.app.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    // No necesitas escribir código aquí por ahora
}
