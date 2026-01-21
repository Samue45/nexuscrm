package com.nexuscrm.app.config;

import com.nexuscrm.app.model.Author;
import com.nexuscrm.app.model.Book;
import com.nexuscrm.app.model.TechDetail;
import com.nexuscrm.app.model.User;
import com.nexuscrm.app.repository.AuthorRepository;
import com.nexuscrm.app.repository.BookRepository;
import com.nexuscrm.app.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;

@Configuration
public class DataGenerator {

    @Bean
    CommandLineRunner initData(AuthorRepository authorRepo, BookRepository bookRepo, UserRepository userRepo) {
        return args -> {
            // Limpieza previa (opcional, útil en desarrollo)
            userRepo.deleteAll();
            bookRepo.deleteAll();
            authorRepo.deleteAll();

            // --- 1. AUTORES ---
            Author a1 = new Author("J.K. Rowling", "Autora británica de Harry Potter.");
            Author a2 = new Author("George R.R. Martin", "Autor de Canción de Hielo y Fuego.");
            Author a3 = new Author("J.R.R. Tolkien", "Creador de la Tierra Media.");
            authorRepo.saveAll(java.util.List.of(a1, a2, a3));

            // --- 2. LIBROS ---
            // Libro 1
            Book b1 = createBook("Harry Potter y la Piedra Filosofal", a1, "9780747532699", 223, LocalDate.of(1997, 6, 26));
            // Libro 2
            Book b2 = createBook("Juego de Tronos", a2, "9780553103540", 694, LocalDate.of(1996, 8, 6));
            // Libro 3 (Nuevo)
            Book b3 = createBook("El Hobbit", a3, "9780261102217", 310, LocalDate.of(1937, 9, 21));
            // Libro 4 (Nuevo)
            Book b4 = createBook("Harry Potter y la Cámara Secreta", a1, "9780747538493", 251, LocalDate.of(1998, 7, 2));

            bookRepo.saveAll(java.util.List.of(b1, b2, b3, b4));

            // --- 3. USUARIOS Y ASIGNACIONES ---
            User u1 = new User("Juan", "Perez", "juan@mail.com", "1234");
            u1.setBirthday(new Date());

            // Asignamos 2 libros a Juan
            assignBookToUser(u1, b1);
            assignBookToUser(u1, b3);

            User u2 = new User("María", "García", "maria@mail.com", "abcd");
            u2.setBirthday(new Date());

            // Asignamos 3 libros a María
            assignBookToUser(u2, b1);
            assignBookToUser(u2, b2);
            assignBookToUser(u2, b4);

            User u3 = new User("Admin", "Nexus", "admin@nexus.com", "admin");
            u3.setBirthday(new Date());

            // El admin tiene todos los libros
            assignBookToUser(u3, b1);
            assignBookToUser(u3, b2);
            assignBookToUser(u3, b3);
            assignBookToUser(u3, b4);

            // Guardamos los usuarios (esto insertará automáticamente en USER_BOOKS)
            userRepo.saveAll(java.util.List.of(u1, u2, u3));

            System.out.println("--- ¡Éxito! Datos generados ---");
            System.out.println("Usuarios creados: " + userRepo.count());
            System.out.println("Relaciones en USER_BOOKS: Generadas correctamente.");
        };
    }

    // Método helper para crear libros rápidamente
    private Book createBook(String title, Author author, String isbn, int pages, LocalDate date) {
        Book book = new Book(title, author);
        book.setPages(pages);
        book.setPublishedDate(date);

        TechDetail td = new TechDetail();
        td.setIsbn(isbn);
        td.setEditorial("Editorial Nexus");
        book.setTechDetail(td);

        return book;
    }

    // Método helper para asegurar la bidireccionalidad (VITAL para ManyToMany)
    private void assignBookToUser(User user, Book book) {
        user.getUserBooks().add(book);
        book.getUsers().add(user);
    }
}