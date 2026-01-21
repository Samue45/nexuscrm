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
            // --- AUTHORS ---
            Author a1 = new Author("J.K. Rowling","Autora británica, famosa por la saga de Harry Potter." );
            Author a2 = new Author("George R.R. Martin","Autor estadounidense, conocido por la saga Canción de Hielo y Fuego.");

            authorRepo.save(a1);
            authorRepo.save(a2);

            // --- BOOKS with TechDetail ---
            Book b1 = new Book("Harry Potter y la Piedra Filosofal", a1);
            b1.setPublishedDate(LocalDate.of(1997, 6, 26));
            b1.setPages(223);
            TechDetail td1 = new TechDetail();
            td1.setIsbn("9780747532699");
            td1.setEditorial("Bloomsbury");
            td1.setPeso(0.5);
            b1.setTechDetail(td1);
            a1.addBook(b1);

            Book b2 = new Book("Juego de Tronos", a2);
            b2.setPublishedDate(LocalDate.of(1996, 8, 6));
            b2.setPages(694);
            TechDetail td2 = new TechDetail();
            td2.setIsbn("9780553103540");
            td2.setEditorial("Bantam Books");
            td2.setPeso(0.7);
            b2.setTechDetail(td2);
            a2.addBook(b2);

            bookRepo.save(b1);
            bookRepo.save(b2);

            // --- USUARIOS ---
            User u1 = new User("Juan", "Perez", "juan@mail.com", "1234");
            u1.setBirthday(new Date());
            HashSet<Book> fav1 = new HashSet<>();
            fav1.add(b1);
            u1.setUserBooks(fav1);

            User u2 = new User("María", "García", "maria@mail.com", "abcd");
            u2.setBirthday(new Date());
            HashSet<Book> fav2 = new HashSet<>();
            fav2.add(b1);
            fav2.add(b2);
            u2.setUserBooks(fav2);

            userRepo.save(u1);
            userRepo.save(u2);

            System.out.println("--- Datos de prueba insertados en H2: autores="
                    + authorRepo.count()
                    + ", libros=" + bookRepo.count()
                    + ", usuarios=" + userRepo.count() + " ---");
        };
    }
}
