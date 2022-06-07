package com.note.noteproject2.controller;

import com.note.noteproject2.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;


    @GetMapping("/")
    public String ViewAllNotesPage(Model model)
    {
        model.addAttribute("notes", noteService.getAllNotes());
        return "notes/notes";
    }

}
