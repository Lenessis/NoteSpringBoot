package com.note.noteproject2.controller;

import com.note.noteproject2.model.Note;
import com.note.noteproject2.service.NoteCategoryServiceImpl;
import com.note.noteproject2.service.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/notes")
public class NoteController {

   @Autowired
    private NoteServiceImpl noteService;
   @Autowired
   private NoteCategoryServiceImpl categoryService;


    /* -- Notes of specific OWNER -- */

    @GetMapping
    public String ViewAllOwnerNotesPage(Model model, String keywords)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        model.addAttribute("username", username);
        model.addAttribute("Categories", categoryService.getAllNoteCategories());

        if(keywords !=null)
            model.addAttribute("ListNote", noteService.getNotesByOwnerAndKeywords(username,keywords));
        else
            model.addAttribute("ListNote", noteService.getNotesByOwner(username, "title", "asc"));

        return "notes/notes";
    }

    /* -- Sorted notes page -- */
    @GetMapping("/")
    public String ViewAllOwnerNotesPage(Model model, String keywords,
                                   @RequestParam("sortField") String sortField,
                                   @RequestParam("sortDir") String sortDir)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        model.addAttribute("username", username);
        model.addAttribute("Categories", categoryService.getAllNoteCategories());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseDir", sortDir.equals("asc")? "desc": "asc");

        if(sortField == null)
            sortField = "title";

        if(sortDir == null)
            sortDir = "asc";

        // list of notes page
        if(keywords !=null)
            model.addAttribute("ListNote", noteService.getNotesByOwnerAndKeywords(username,keywords));
        else
            model.addAttribute("ListNote", noteService.getNotesByOwner(username, sortField, sortDir));

        return "notes/notes";
    }

    /* -- Filtered by category notes page -- */
    @GetMapping("/categories")
    public String ViewAllOwnerNotesPage2(Model model, String categoryFilter)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        model.addAttribute("username", username);
        model.addAttribute("Categories", categoryService.getAllNoteCategories());

        List<Note> notes = noteService.getNotesByOwnerAndCategory(username, categoryFilter);

        if(notes.isEmpty())
            model.addAttribute("ListNote", null);
        else
            model.addAttribute("ListNote", notes );

        return "notes/notes";
    }

    /* -- PUBLIC notes page -- */



    /* -- Add note -- */

    @GetMapping("/add")
    public String AddNewNotePage(Model model)
    {
        // add note Page
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Note note = new Note();
        note.setOwner(auth.getName());
        model.addAttribute("newNote",note);
        model.addAttribute("noteCategory",categoryService.getAllNoteCategories());
        return "notes/notes_add";
    }

    @PostMapping("/add-note")
    public  String AddNote(@Valid @ModelAttribute("newNote") Note newNote, Errors errors, Model model)
    {
        model.addAttribute("noteCategory",categoryService.getAllNoteCategories());
        if(errors.hasErrors())
            return "notes/notes_add";

        else
        {
            // add note to Database
            noteService.saveNote(newNote);
            return "redirect:/notes";
        }

    }

    @GetMapping("/update/{id}")
    public String UpdateNotePage(@PathVariable (value = "id") long id, Model model)
    {
        // get note by id
        Note note = noteService.getNoteById(id);

        // set note as a model attribute
        model.addAttribute("note", note);
        model.addAttribute("noteCategory",categoryService.getAllNoteCategories());
        return "notes/notes_update";
    }

    @PostMapping("/update-note")
    public String UpdateNote(@Valid @ModelAttribute("note") Note newNote, Errors errors, Model model)
    {
        model.addAttribute("noteCategory",categoryService.getAllNoteCategories());
        if(errors.hasErrors())
            return "notes/notes_update";

        else
        {
            noteService.saveNote(newNote);
            return "redirect:/notes";
        }
    }

    @GetMapping("/delete/{id}")
    public String DeleteNotePage(@PathVariable (value = "id") long id)
    {
        this.noteService.deleteNoteById(id);
        return "redirect:/notes";
    }





    /* -- Default notes page  - previous version; I won't delete it to leave some notes/ knowledge for future -- */

    /*@GetMapping
    public String ViewAllNotesPage(Model model, String keywords)
    {
        model.addAttribute("Categories", categoryService.getAllNoteCategories());

        *//*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();*//*

        //model.addAttribute("username", username);

        // list of notes page
        if(keywords !=null)
            model.addAttribute("ListNote", noteService.getNotesByKeywords(keywords));
        else
            model.addAttribute("ListNote", noteService.getAllNotes("title", "asc"));

        return "notes/notes";
    }

    *//* -- Sorted notes page -- *//*
    @GetMapping("/")
    public String ViewAllNotesPage(Model model, String keywords,
                                   @RequestParam("sortField") String sortField,
                                   @RequestParam("sortDir") String sortDir)
    {
        model.addAttribute("Categories", categoryService.getAllNoteCategories());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseDir", sortDir.equals("asc")? "desc": "asc");

        if(sortField == null)
            sortField = "title";

        if(sortDir == null)
            sortDir = "asc";

        // list of notes page
        if(keywords !=null)
            model.addAttribute("ListNote", noteService.getNotesByKeywords(keywords));
        else
            model.addAttribute("ListNote", noteService.getAllNotes(sortField,sortDir));

        return "notes/notes";
    }

    *//* -- Filtered by category notes page -- *//*
    @GetMapping("/categories")
    public String ViewAllNotesPage2(Model model, String categoryFilter)
    {
        model.addAttribute("Categories", categoryService.getAllNoteCategories());
        List<Note> notes = noteService.getNotesByCategory(categoryFilter);
        if(notes.isEmpty())
            model.addAttribute("ListNote", null);
        else
            model.addAttribute("ListNote", notes );

        return "notes/notes";
    }*/


}
