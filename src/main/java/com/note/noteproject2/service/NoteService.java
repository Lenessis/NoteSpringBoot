package com.note.noteproject2.service;

import com.note.noteproject2.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NoteService {
    List<Note> getAllNotes();
}
