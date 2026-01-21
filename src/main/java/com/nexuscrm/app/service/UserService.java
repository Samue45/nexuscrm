package com.nexuscrm.app.service;

import com.nexuscrm.app.model.Book;
import com.nexuscrm.app.model.User;
import com.nexuscrm.app.repository.UserRepository;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository repository;

    // Inyección por constructor (Spring la detecta automáticamente)
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    // Listar todas las personas
    public List<User> findAll() {
        return repository.findAll();
    }

    public User findUserByName(String name){
        return repository.findUserByName(name);
    }

    // Guardar o actualizar una persona
    public User save(User user) {
        if (user == null) {
            System.err.println("La persona es nula.");
            return user;
        }
        repository.save(user);
        return user;
    }

    // Eliminar una persona
    public void delete(User user) {
        repository.delete(user);
    }

    public User findByEmail(String mail) {
        return repository.findByEmail(mail);
    }

    @Transactional(readOnly = true)
    public Set<Book> findBooksForCurrentUser() {
        User sessionUser = (User) VaadinSession.getCurrent().getAttribute("usuarioLogueado");
        if (sessionUser == null) return Collections.emptySet();

        // Esta consulta trae todo el grafo de objetos necesario
        User user = repository.findUserByNameWithBooksAndAuthors(sessionUser.getName());

        return (user != null) ? user.getUserBooks() : Collections.emptySet();
    }
}
