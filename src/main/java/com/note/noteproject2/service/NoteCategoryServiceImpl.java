package com.note.noteproject2.service;

import com.note.noteproject2.model.Note;
import com.note.noteproject2.model.NoteCategory;
import com.note.noteproject2.repository.NoteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;

@Service
public class NoteCategoryServiceImpl implements NoteCategoryService{

    @Autowired
    private NoteCategoryRepository noteCategoryRepository;

    @Override
    public List<NoteCategory> getAllNoteCategories() {
        return noteCategoryRepository.findAll();
    }

    @Override
    public List<NoteCategory> getAllNoteCategories(String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        return noteCategoryRepository.findAll(sort);
    }

    @Override
    public NoteCategory getNoteCategoryById(long id) {

        Optional<NoteCategory> optional = this.noteCategoryRepository.findById(id);
        NoteCategory noteCategory = null;

        if(optional.isPresent())
        {
            noteCategory = optional.get();
        }
        else
        {
            throw new RuntimeException("Category "+id+" not found!");
        }

        return noteCategory;
    }

    @Override
    public void saveNoteCategory(NoteCategory noteCategory) {
        this.noteCategoryRepository.save(noteCategory);
    }

    @Override
    public void deleteNoteCategory(long id) {
        this.noteCategoryRepository.deleteById(id);
    }

    @Override
    public List<NoteCategory> getNoteCategoriesByKeywords(String keywords) {
        return this.noteCategoryRepository.findByKeywords(keywords);
    }
}
