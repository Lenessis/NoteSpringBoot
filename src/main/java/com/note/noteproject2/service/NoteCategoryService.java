package com.note.noteproject2.service;

import com.note.noteproject2.model.NoteCategory;
import org.springframework.stereotype.Service;

import java.util.List;


public interface NoteCategoryService {
    List<NoteCategory> getAllNoteCategories();
    NoteCategory getNoteCategoryById(long id);
    void saveNoteCategory(NoteCategory noteCategory);
    void deleteNoteCategory(long id);
}
