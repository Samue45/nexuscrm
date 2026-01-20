package com.nexuscrm.app.service;


import com.nexuscrm.app.model.Author;
import com.nexuscrm.app.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService (AuthorRepository authorRepository){this.authorRepository = authorRepository;}

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Author createNewAuthor(Author newAuthor){
        return authorRepository.save(newAuthor);
    }

    public void delete(Author author){
        authorRepository.delete(author);
    }
}
