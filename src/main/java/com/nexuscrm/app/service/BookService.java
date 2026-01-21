package com.nexuscrm.app.service;

import com.nexuscrm.app.dto.BookDTO;
import com.nexuscrm.app.model.Book;
import com.nexuscrm.app.model.User;
import com.nexuscrm.app.repository.BookRepository;
import com.nexuscrm.app.repository.UserRepository;
import com.vaadin.flow.server.VaadinSession;
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


    @Transactional(readOnly = true)
    public List<BookDTO> findBooksDTOForCurrentUser() {
        User sessionUser = (User) VaadinSession.getCurrent().getAttribute("usuarioLogueado");
        if (sessionUser == null) return List.of();

        // Buscamos al usuario y transformamos su Set<Book> a List<BookDTO>
        return userRepository.findById(sessionUser.getId())
                .map(user -> user.getUserBooks().stream()
                        .map(this::mapToDTO)
                        .toList())
                .orElse(List.of());
    }

    public List<Book> findByAuthorName(String name) {
        return bookRepository.findByAuthor_Name(name);
    }

    /**
     * Método conversor (Mapper)
     * Transforma una Entidad JPA en un objeto plano DTO.
     */
    private BookDTO mapToDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setPages(book.getPages());
        dto.setPublishedDate(book.getPublishedDate());

        // Aplanamos la relación con Author
        if (book.getAuthor() != null) {
            dto.setAuthorName(book.getAuthor().getName());
        }

        // Aplanamos la relación con TechDetail
        if (book.getTechDetail() != null) {
            dto.setIsbn(book.getTechDetail().getIsbn());
            dto.setEditorial(book.getTechDetail().getEditorial());
        }

        return dto;
    }
}
