package com.note.noteproject2.controller;

import com.note.noteproject2.model.Note;
import com.note.noteproject2.service.NoteService;
import com.note.noteproject2.service.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/notes")
public class NoteController {

   @Autowired
    private NoteServiceImpl noteService;


    @GetMapping
    public String ViewAllNotesPage(Model model)
    {
        model.addAttribute("ListNote", noteService.getAllNotes());
        return "notes/notes";
    }

}
