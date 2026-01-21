package com.nexuscrm.app;

import com.nexuscrm.app.model.Author;
import com.nexuscrm.app.model.Book;
import com.nexuscrm.app.repository.BookRepository;
import com.nexuscrm.app.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void testSearchBooksByAuthor(){
        // 1ºCreate example data
        Author authorMock = new Author("Gabriel García Márquez","Hello");
        List<Book> fictitiousBooks = List.of(
                new Book("Cien años de soledad", authorMock),
                new Book("El amor en los tiempos del cólera", authorMock)
        );

        //2º We create an example query using Mockito
        when(bookRepository.findByAuthorName("Gabriel García Márquez")).thenReturn(fictitiousBooks);

        //3º We search an author by name to can get his books
        List<Book> result = bookService.findByAuthorName("Gabriel García Márquez");

        // 3. Assert (Verificar)
        assertEquals(2, result.size());
        verify(bookRepository).findByAuthorName("Gabriel García Márquez");
    }
}