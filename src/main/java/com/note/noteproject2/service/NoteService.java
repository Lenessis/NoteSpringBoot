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
    List<Note> getNotesByCategory(String categoryFilter); /*wyszukiwanie po kategoriach*/
}
