package com.bookstore.BookStore.Project.controller;


import com.bookstore.BookStore.Project.exception.ResourceNotFoundException;
import com.bookstore.BookStore.Project.model.Book;
import com.bookstore.BookStore.Project.repository.BookRepository;

import org.hibernate.annotations.ManyToAny;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    //Get all Books
    @GetMapping("/get_book")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    //Create a New Book
    @PostMapping("/create_book")
    public Book createBook(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }

    //Get a single Book
    @GetMapping("/get_single_book/{id}")
    public Book getBookById(@PathVariable(value = "id") Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
    }

    //Update a Book
    @PutMapping("/update_book/{id}")
    public Book updateBook(@PathVariable(value = "id") Long bookId,
                                           @Valid @RequestBody Book bookDetails) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));

        book.setBookTitle(bookDetails.getBookTitle());
        book.setBookDescription(bookDetails.getBookDescription());
        book.setBookPrice(bookDetails.getBookPrice());
        book.setPublisher(bookDetails.getPublisher());
        book.setAuthor(bookDetails.getAuthor());
        book.setCategory(bookDetails.getCategory());

        Book updatedBook = bookRepository.save(book);
        return updatedBook;
    }

    //Delete a Book
    @DeleteMapping("/delete_book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));

        bookRepository.delete(book);

        return ResponseEntity.ok().build();
    }
}