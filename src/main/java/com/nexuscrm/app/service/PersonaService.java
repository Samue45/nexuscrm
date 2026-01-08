package com.nexuscrm.app.service;

import com.nexuscrm.app.model.Persona;
import com.nexuscrm.app.repository.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {

    private final PersonaRepository repository;

    // Inyección por constructor (Spring la detecta automáticamente)
    public PersonaService(PersonaRepository repository) {
        this.repository = repository;
    }

    // Listar todas las personas
    public List<Persona> findAll() {
        return repository.findAll();
    }

    // Guardar o actualizar una persona
    public void save(Persona persona) {
        if (persona == null) {
            System.err.println("La persona es nula.");
            return;
        }
        repository.save(persona);
    }

    // Eliminar una persona
    public void delete(Persona persona) {
        repository.delete(persona);
    }
}
