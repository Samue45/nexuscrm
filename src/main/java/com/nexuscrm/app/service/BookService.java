package com.nexuscrm.app.service;

import com.nexuscrm.app.model.Book;
import com.nexuscrm.app.model.User;
import com.nexuscrm.app.repository.BookRepository;
import com.nexuscrm.app.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /* ===================== */
    /* LÓGICA DE NEGOCIO     */
    /* FAVORITOS / LECTURA   */
    /* ===================== */

    @Transactional
    public void addToFavorites(Long userId, Long bookId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Book book = findById(bookId);

        // ❌ Regla de negocio: no duplicados
        if (user.getFavoriteBooks().contains(book)) {
            throw new IllegalStateException("El libro ya está en favoritos");
        }

        user.getFavoriteBooks().add(book);

        // Guardamos el lado propietario
        userRepository.save(user);
    }

    @Transactional
    public void removeFromFavorites(Long userId, Long bookId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        boolean removed = user.getFavoriteBooks()
                .removeIf(book -> book.getId().equals(bookId));

        if (removed) {
            userRepository.save(user);
        }
    }

    public List<Book> getUserFavorites(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        return List.copyOf(user.getFavoriteBooks());
    }
}
