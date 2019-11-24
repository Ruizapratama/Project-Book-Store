package com.bookstore.BookStore.Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.BookStore.Project.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}