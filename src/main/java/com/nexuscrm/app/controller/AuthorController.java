package com.nexuscrm.app.controller;

import com.nexuscrm.app.model.Author;
import com.nexuscrm.app.service.AuthorService;
import org.atmosphere.config.service.Delete;
import org.atmosphere.config.service.Get;
import org.atmosphere.config.service.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService){this.authorService = authorService;}

    @Get
    public List<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @Post
    public Author createNewAuthor (Author newAuthor){
        return authorService.createNewAuthor(newAuthor);
    }

    @Delete
    public void delete(Author author){
        authorService.delete(author);
    }
}
