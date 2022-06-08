package com.note.noteproject2.service;

import com.note.noteproject2.model.Note;
import com.note.noteproject2.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Note getNoteById(long id) {
        Optional<Note> optional = noteRepository.findById(id);
        Note note = null;
        if(optional.isPresent())
        {
            note = optional.get();
        }
        else
        {
            throw new RuntimeException("Note for id: " + id +" not found!");
        }
        return note;
    }

    @Override
    public void saveNote(Note note) {
        this.noteRepository.save(note);
    }

}
