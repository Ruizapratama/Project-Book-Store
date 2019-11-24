package com.bookstore.BookStore.Project.controller;


import com.bookstore.BookStore.Project.exception.ResourceNotFoundException;
import com.bookstore.BookStore.Project.model.Notes;
import com.bookstore.BookStore.Project.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    //Get all Notes
    @GetMapping("/get_notes")
    public List<Notes> getAllNotes() {
        return noteRepository.findAll();
    }

    //Create a New Note
    @PostMapping("/create_notes")
    public Notes createNote(@Valid @RequestBody Notes note) {
        return noteRepository.save(note);
    }

    //Get a single note
    @GetMapping("/get_single_notes/{id}")
    public Notes getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    //Update a Note
    @PutMapping("/update_notes/{id}")
    public Notes updateNote(@PathVariable(value = "id") Long noteId,
                                           @Valid @RequestBody Notes noteDetails) {

        Notes note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Notes updatedNote = noteRepository.save(note);
        return updatedNote;
    }

    //Delete a Note
    @DeleteMapping("/delete_notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        Notes note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}