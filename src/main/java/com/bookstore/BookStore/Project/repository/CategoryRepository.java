package com.bookstore.BookStore.Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.BookStore.Project.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}