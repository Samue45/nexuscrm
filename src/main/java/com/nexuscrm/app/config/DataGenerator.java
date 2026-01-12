package com.nexuscrm.app.config;

import com.nexuscrm.app.model.User;
import com.nexuscrm.app.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataGenerator {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            repository.save(new User("Juan ","Perez", "juan@mail.com"));
            repository.save(new User("Maria ","Garcia", "maria@mail.com"));
            repository.save(new User("Luis ", "Lopez", "luis@mail.com"));

            System.out.println("--- Datos de prueba insertados en H2 ---");
        };
    }
}