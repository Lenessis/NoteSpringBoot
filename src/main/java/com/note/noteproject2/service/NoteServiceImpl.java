package com.note.noteproject2.service;

import com.note.noteproject2.model.Note;
import com.note.noteproject2.repository.NoteRepository;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public List<Note> getAllNotes(String sortField, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        return noteRepository.findAll(sort);
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

    @Override
    public void deleteNoteById(long id) {
        this.noteRepository.deleteById(id);
    }

    @Override
    public List<Note> getNotesByKeywords(String keywords) {
        return this.noteRepository.findByKeywords(keywords);
    }

    @Override
    public List<Note> getNotesByCategory(String categoryFilter) {
        return this.noteRepository.findByCategory(categoryFilter);
    }

}
