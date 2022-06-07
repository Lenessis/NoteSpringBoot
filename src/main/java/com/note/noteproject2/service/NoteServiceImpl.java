package com.note.noteproject2.service;

import com.note.noteproject2.model.Note;
import com.note.noteproject2.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NoteServiceImpl implements NoteService{

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }
}
