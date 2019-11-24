package com.bookstore.BookStore.Project.controller;


import com.bookstore.BookStore.Project.exception.ResourceNotFoundException;
import com.bookstore.BookStore.Project.model.Category;
import com.bookstore.BookStore.Project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    //Get all Category
    @GetMapping("/get_category")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    //Create a New Category
    @PostMapping("/create_category")
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryRepository.save(category);
    }

    //Get a single Category
    @GetMapping("/get_single_category/{id}")
    public Category getCategoryById(@PathVariable(value = "id") Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
    }

    //Update a Category
    @PutMapping("/update_category/{id}")
    public Category updatePublisher(@PathVariable(value = "id") Long categoryId,
                                           @Valid @RequestBody Category categoryDetails) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category"
                		+ "", "id", categoryId));

        category.setCategoryName(categoryDetails.getCategoryName());
        
        category.setCategoryDescription(categoryDetails.getCategoryDescription());

        Category updatedCategory = categoryRepository.save(category);
        return updatedCategory;
    }

    //Delete a Category
    @DeleteMapping("/delete_category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(value = "id") Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        categoryRepository.delete(category);

        return ResponseEntity.ok().build();
    }
}