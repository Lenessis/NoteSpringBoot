package com.note.noteproject2.service;

import com.note.noteproject2.model.NoteCategory;
import com.note.noteproject2.repository.NoteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteCategoryServiceImpl implements NoteCategoryService{

    @Autowired
    private NoteCategoryRepository noteCategoryRepository;

    @Override
    public List<NoteCategory> getAllNotesCategory() {
        return noteCategoryRepository.findAll();
    }
}
