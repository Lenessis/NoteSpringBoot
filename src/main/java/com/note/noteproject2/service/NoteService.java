package com.note.noteproject2.service;

import com.note.noteproject2.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NoteService {
    List<Note> getAllNotes(String sortField, String sortDirection);
    Note getNoteById(long id);
    void saveNote(Note note);
    void deleteNoteById(long id);
    List<Note> getNotesByKeywords(String keywords);
    List<Note> getNotesByCategory(String categoryFilter);

    List<Note> getNotesByOwner(String owner, String sortField, String sortDirection);
    List<Note> getNotesByOwnerAndKeywords(String owner, String keywords);
    List<Note> getNotesByOwnerAndCategory(String owner, String categoryFilter);

    List<Note> getPublicNotes(String sortField, String sortDirection);
    List<Note> getPublicNotesByKeywords(String keywords);
    List<Note> getPublicNotesByCategory(String categoryFilter);
}
