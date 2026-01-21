package com.nexuscrm.app.service;

import com.nexuscrm.app.model.Book;
import com.nexuscrm.app.model.User;
import com.nexuscrm.app.repository.BookRepository;
import com.nexuscrm.app.repository.UserRepository;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository,
                       UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    /* ===================== */
    /* CRUD BÁSICO DE BOOK   */
    /* ===================== */

    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado con id " + id));
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book update(Long id, Book data) {
        Book book = findById(id);

        book.setTitle(data.getTitle());
        book.setPublishedDate(data.getPublishedDate());
        book.setPages(data.getPages());
        book.setAuthor(data.getAuthor());
        book.setTechDetail(data.getTechDetail());

        return bookRepository.save(book);
    }


    public void delete(Long id) {
        bookRepository.deleteById(id);
    }


    @Transactional
    public Set<Book> findBooksForCurrentUser() {
        // 1. Obtener el usuario de la sesión de Vaadin
        User sessionUser = (User) VaadinSession.getCurrent().getAttribute("usuarioLogueado");

        if (sessionUser == null) return new HashSet<>();

        // 2. IMPORTANTE: Volver a cargar el usuario desde el repositorio
        // para que la sesión de Hibernate esté activa y pueda leer los libros.
        return userRepository.findById(sessionUser.getId())
                .map(User::getUserBooks)
                .orElse(new HashSet<>());
    }

    public List<Book> findByAuthorName(String name) {
        return bookRepository.findByAuthor_Name(name);
    }
}
