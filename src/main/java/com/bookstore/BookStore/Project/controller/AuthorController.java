package com.bookstore.BookStore.Project.controller;


import com.bookstore.BookStore.Project.exception.ResourceNotFoundException;
import com.bookstore.BookStore.Project.model.Author;
import com.bookstore.BookStore.Project.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    //Get all Authors
    @GetMapping("/get_author")
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    //Create a New Author
    @PostMapping("/create_author")
    public Author createAuthor(@Valid @RequestBody Author author) {
        return authorRepository.save(author);
    }

    //Get a single Author
    @GetMapping("/get_single_author/{id}")
    public Author getBookById(@PathVariable(value = "id") Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", authorId));
    }

    //Update an Author
    @PutMapping("/update_author/{id}")
    public Author updateAuthor(@PathVariable(value = "id") Long authorId,
                                           @Valid @RequestBody Author authorDetails) {

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", authorId));

        author.setFirstName(authorDetails.getFirstName());
        
        author.setLastName(authorDetails.getLastName());

        Author updatedAuthor = authorRepository.save(author);
        return updatedAuthor;
    }

    //Delete an Author
    @DeleteMapping("/delete_author/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", authorId));

        authorRepository.delete(author);

        return ResponseEntity.ok().build();
    }
}