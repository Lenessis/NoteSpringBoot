package com.note.noteproject2.controller;

import com.note.noteproject2.model.Note;
import com.note.noteproject2.service.NoteCategoryServiceImpl;
import com.note.noteproject2.service.NoteService;
import com.note.noteproject2.service.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/notes")
public class NoteController {

   @Autowired
    private NoteServiceImpl noteService;
   @Autowired
   private NoteCategoryServiceImpl categoryService;


    @GetMapping
    public String ViewAllNotesPage(Model model)
    {
        // list of notes page
        model.addAttribute("ListNote", noteService.getAllNotes());
        return "notes/notes";
    }

    @GetMapping("/add")
    public String AddNewNotePage(Model model)
    {
        // add note Page
        Note note = new Note();
        model.addAttribute("newNote",note);
        model.addAttribute("noteCategory",categoryService.getAllNotesCategory());
        return "notes/notes_add";
    }

    @PostMapping("/add-note")
    public  String AddNote(@ModelAttribute("newNote") Note newNote)
    {
        // add note to Database
        noteService.saveNote(newNote);
        return "redirect:/notes";
    }

}
