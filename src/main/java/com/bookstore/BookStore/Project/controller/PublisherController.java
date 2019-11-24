package com.bookstore.BookStore.Project.controller;


import com.bookstore.BookStore.Project.exception.ResourceNotFoundException;
import com.bookstore.BookStore.Project.model.Publisher;
import com.bookstore.BookStore.Project.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    PublisherRepository publisherRepository;

    //Get all Publishers
    @GetMapping("/get_publisher")
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    //Create a New Publisher
    @PostMapping("/create_publisher")
    public Publisher createAuthor(@Valid @RequestBody Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    //Get a single Publisher
    @GetMapping("/get_single_Publisher/{id}")
    public Publisher getPublisherById(@PathVariable(value = "id") Long publisherId) {
        return publisherRepository.findById(publisherId)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher", "id", publisherId));
    }

    //Update a Publisher
    @PutMapping("/update_publisher/{id}")
    public Publisher updatePublisher(@PathVariable(value = "id") Long publisherId,
                                           @Valid @RequestBody Publisher publisherDetails) {

        Publisher publisher = publisherRepository.findById(publisherId)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher", "id", publisherId));

        publisher.setPublisherName(publisherDetails.getPublisherName());
        
        publisher.setCountryName(publisherDetails.getCountryName());
        
        publisher.setGrade(publisherDetails.getGrade());

        Publisher updatedPublisher = publisherRepository.save(publisher);
        return updatedPublisher;
    }

    //Delete a Publisher
    @DeleteMapping("/delete_publisher/{id}")
    public ResponseEntity<?> deletePublisher(@PathVariable(value = "id") Long publisherId) {
        Publisher publisher = publisherRepository.findById(publisherId)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher", "id", publisherId));

        publisherRepository.delete(publisher);

        return ResponseEntity.ok().build();
    }
}