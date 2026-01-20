package com.nexuscrm.app;

import com.nexuscrm.app.model.User;
import com.nexuscrm.app.repository.UserRepository;
import com.nexuscrm.app.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreateNewUser(){
        // 1º Datos de ejemplo
        User user1 = new User("Samuel", "Ayllón Sevilla", "samuel@gmail.com");

        // Entrenamos al mock: "Cuando busquen a Samuel, devuelve este objeto user1"
        when(userRepository.findUserByName("Samuel")).thenReturn(user1);

        // 2º Ejecutar la lógica
        // Llamamos al método del SERVICIO que internamente usará el repositorio
        User resultado = userService.findUserByName("Samuel");

        // 3º Verificar resultados (Assert)
        assertNotNull(resultado);
        assertEquals("Samuel", resultado.getName());

        // Verificamos que el repositorio fue consultado exactamente una vez
        verify(userRepository, times(1)).findUserByName("Samuel");
    }
}