package com.nexuscrm.app;

import com.nexuscrm.app.model.User;
import com.nexuscrm.app.repository.UserRepository;
import com.nexuscrm.app.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    // --- Test: Buscar usuario por email ---
    @Test
    void testFindByEmail() {
        User user = new User("Samuel", "Ayllón", "samuel@gmail.com", "1234");

        when(userRepository.findByEmail("samuel@gmail.com")).thenReturn(user);

        User result = userService.findByEmail("samuel@gmail.com");

        assertNotNull(result);
        assertEquals("samuel@gmail.com", result.getEmail());
        verify(userRepository, times(1)).findByEmail("samuel@gmail.com");
    }

    // --- Test: Buscar usuario inexistente ---
    @Test
    void testFindByEmailNotFound() {
        when(userRepository.findByEmail("noexist@mail.com")).thenReturn(null);

        User result = userService.findByEmail("noexist@mail.com");

        assertNull(result);
        verify(userRepository, times(1)).findByEmail("noexist@mail.com");
    }

    // --- Test: Validar contraseña ---
    @Test
    void testPasswordValidation() {
        User user = new User("Juan", "Perez", "juan@mail.com", "abcd");
        when(userRepository.findByEmail("juan@mail.com")).thenReturn(user);

        User result = userService.findByEmail("juan@mail.com");
        assertEquals("abcd", result.getPassword()); // Validación simple
    }

    // --- Test: Guardar usuario ---
    @Test
    void testSaveUser() {
        User user = new User("María", "García", "maria@mail.com", "1234");

        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        assertEquals("maria@mail.com", savedUser.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    // --- Test: Guardar usuario nulo ---
    @Test
    void testSaveUserNull() {
        User result = userService.save(null);

        assertNull(result);
        verify(userRepository, never()).save(any());
    }

    // --- Test: Eliminar usuario ---
    @Test
    void testDeleteUser() {
        User user = new User("Pedro", "López", "pedro@mail.com", "1234");

        doNothing().when(userRepository).delete(user);

        userService.delete(user);

        verify(userRepository, times(1)).delete(user);
    }

    // --- Test: Listar todos los usuarios ---
    @Test
    void testFindAllUsers() {
        User u1 = new User("A", "B", "a@mail.com", "1");
        User u2 = new User("C", "D", "c@mail.com", "2");

        when(userRepository.findAll()).thenReturn(Arrays.asList(u1, u2));

        List<User> users = userService.findAll();

        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }
}
