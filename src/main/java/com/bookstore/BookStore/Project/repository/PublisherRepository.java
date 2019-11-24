package com.bookstore.BookStore.Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.BookStore.Project.model.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}