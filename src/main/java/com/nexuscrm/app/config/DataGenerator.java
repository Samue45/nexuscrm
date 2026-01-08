package com.nexuscrm.app.config;

import com.nexuscrm.app.model.Persona;
import com.nexuscrm.app.repository.PersonaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataGenerator {

    @Bean
    CommandLineRunner initDatabase(PersonaRepository repository) {
        return args -> {
            repository.save(new Persona("Juan ","Perez", "juan@mail.com"));
            repository.save(new Persona("Maria ","Garcia", "maria@mail.com"));
            repository.save(new Persona("Luis ", "Lopez", "luis@mail.com"));

            System.out.println("--- Datos de prueba insertados en H2 ---");
        };
    }
}