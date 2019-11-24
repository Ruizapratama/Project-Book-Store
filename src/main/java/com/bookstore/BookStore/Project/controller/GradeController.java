package com.bookstore.BookStore.Project.controller;


import com.bookstore.BookStore.Project.exception.ResourceNotFoundException;
import com.bookstore.BookStore.Project.model.Grade;
import com.bookstore.BookStore.Project.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    GradeRepository gradeRepository;

    //Get all Grade
    @GetMapping("/get_grade")
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    //Create a New Grade
    @PostMapping("/create_grade")
    public Grade createGrade(@Valid @RequestBody Grade grade) {
        return gradeRepository.save(grade);
    }

    //Get a single Grade
    @GetMapping("/get_single_grade/{id}")
    public Grade getGradeById(@PathVariable(value = "id") Long gradeId) {
        return gradeRepository.findById(gradeId)
                .orElseThrow(() -> new ResourceNotFoundException("Grade", "id", gradeId));
    }

    //Update a Grade
    @PutMapping("/update_grade/{id}")
    public Grade updatePublisher(@PathVariable(value = "id") Long gradeId,
                                           @Valid @RequestBody Grade gradeDetails) {

        Grade grade = gradeRepository.findById(gradeId)
                .orElseThrow(() -> new ResourceNotFoundException("Grade", "id", gradeId));

        grade.setBookQuality(gradeDetails.getBookQuality());
        
        grade.setBookBaseProduction(gradeDetails.getBookBaseProduction());

        Grade updatedGrade = gradeRepository.save(grade);
        return updatedGrade;
    }

    //Delete a Grade
    @DeleteMapping("/delete_grade/{id}")
    public ResponseEntity<?> deletePublisher(@PathVariable(value = "id") Long gradeId) {
        Grade grade = gradeRepository.findById(gradeId)
                .orElseThrow(() -> new ResourceNotFoundException("Grade", "id", gradeId));

        gradeRepository.delete(grade);

        return ResponseEntity.ok().build();
    }
}