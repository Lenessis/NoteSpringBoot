package com.note.noteproject2.service;

import com.note.noteproject2.model.Note;
import com.note.noteproject2.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    private NoteRepository noteRepository;

    /* -- All notes -- */

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
            note = optional.get();

        else
            throw new RuntimeException("Note for id: " + id +" not found!");

        return note;
    }

    /* -- Save & Delete notes -- */

    @Override
    public void saveNote(Note note) {
        this.noteRepository.save(note);
    }

    @Override
    public void deleteNoteById(long id) {
        this.noteRepository.deleteById(id);
    }

    /* -- Filter All -- */

    @Override
    public List<Note> getNotesByKeywords(String keywords) {
        return this.noteRepository.findByKeywords(keywords);
    }

    @Override
    public List<Note> getNotesByCategory(String categoryFilter) {
        return this.noteRepository.findByCategory(categoryFilter);
    }

    /* -- Owner -- */

     @Override
    public List<Note> getNotesByOwner(String owner, String sortField, String sortDirection) {
         if(sortField.equals("title"))
             if(sortDirection.equals("asc"))
                return noteRepository.findNotesByOwnerTitleASC(owner);
             else
                 return noteRepository.findNotesByOwnerTitleDESC(owner);
         else
            if(sortDirection.equals("asc"))
                return noteRepository.findNotesByOwnerDateASC(owner);
            else
                return  noteRepository.findNotesByOwnerDateDESC(owner);
    }

    @Override
    public List<Note> getNotesByOwnerAndKeywords(String owner, String keywords) {
        return this.noteRepository.findNotesByOwnerAndKeywords(owner, keywords);
    }

    @Override
    public List<Note> getNotesByOwnerAndCategory(String owner, String categoryFilter) {
        return this.noteRepository.findNotesByOwnerAndCategory(owner,categoryFilter);
    }

    /* -- Public -- */

    @Override
    public List<Note> getPublicNotes(String sortField, String sortDirection) {

        if(sortField.equals("title"))
            if(sortDirection.equals("asc"))
                return noteRepository.findPublicNotesByTitleASC();
            else
                return noteRepository.findPublicNotesByTitleDESC();
        else
        if(sortDirection.equals("asc"))
            return noteRepository.findPublicNotesByDateASC();
        else
            return  noteRepository.findPublicNotesByDateDESC();
    }

    @Override
    public List<Note> getPublicNotesByKeywords(String keywords) {
        return this.noteRepository.findPublicNotesByKeywords(keywords);
    }

    @Override
    public List<Note> getPublicNotesByCategory(String categoryFilter) {
        return this.noteRepository.findPublicNotesByCategory(categoryFilter);
    }

}
