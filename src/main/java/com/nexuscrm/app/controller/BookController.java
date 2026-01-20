package com.nexuscrm.app.controller;

import com.nexuscrm.app.model.Book;
import com.nexuscrm.app.service.BookService;
import org.atmosphere.config.service.Get;
import org.atmosphere.config.service.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController (BookService bookService){
        this.bookService = bookService;
    }

    @Get
    public List<Book> getAllBooks(){
        return bookService.findAll();
    }

    @Post
    public Book createNewBook(@RequestBody Book newBook){
        return bookService.create(newBook);
    }
}
