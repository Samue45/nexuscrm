package com.nexuscrm.app.service;

import com.nexuscrm.app.dto.UserDTO;
import com.nexuscrm.app.model.User;
import com.nexuscrm.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j                  // Reemplaza System.err por un logger profesional
@Service
@RequiredArgsConstructor // Crea el constructor para 'repository' automáticamente (Lombok)
public class UserService {

    private final UserRepository repository;

    // --- CONSULTAS ---

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findUserByName(String name) {
        return repository.findUserByName(name);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    /**
     * Obtiene el DTO de un usuario por nombre.
     * Uso de Optional para evitar retornos null.
     */
    public Optional<UserDTO> getUserInfo(String username) {
        return Optional.ofNullable(repository.findUserByName(username))
                .map(this::mapToDTO);
    }

    // --- OPERACIONES DE PERSISTENCIA ---

    @Transactional
    public User save(User user) {
        if (user == null) {
            log.error("Intento de guardar un usuario nulo.");
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        return repository.save(user);
    }

    @Transactional
    public void delete(User user) {
        repository.delete(user);
    }

    // --- MAPEO (Lógica de conversión privada) ---

    private UserDTO mapToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getBirthday()
        );
    }
}