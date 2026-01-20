package com.nexuscrm.app.repository;

import com.nexuscrm.app.model.Book;
import org.reactivestreams.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAuthorByName(String name);
}
