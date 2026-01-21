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


    public List<Book> getUserBooks(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        return List.copyOf(user.getUserBooks());
    }

    public List<Book> findByAuthorName(String name) {
        return bookRepository.findByAuthorName(name);
    }
}
