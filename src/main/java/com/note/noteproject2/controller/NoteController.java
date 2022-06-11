package com.note.noteproject2.controller;

import com.note.noteproject2.model.Note;
import com.note.noteproject2.service.NoteCategoryServiceImpl;
import com.note.noteproject2.service.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/notes")
public class NoteController {

   @Autowired
    private NoteServiceImpl noteService;
   @Autowired
   private NoteCategoryServiceImpl categoryService;


    @GetMapping
    public String ViewAllNotesPage(Model model, String keywords/*, String categoryFilter*/)
    {
        model.addAttribute("Categories", categoryService.getAllNoteCategories());


        // list of notes page
        if(keywords !=null)
            model.addAttribute("ListNote", noteService.getNotesByKeywords(keywords));
        /*else if (categoryFilter!="null")
            model.addAttribute("ListNote",noteService.getNotesByCategory(categoryFilter));*/
        else
            model.addAttribute("ListNote", noteService.getAllNotes("title", "asc"));

        return "notes/notes";
    }

    @GetMapping("/")
    public String ViewAllNotesPage(Model model, String keywords/*, String categoryFilter*/,
                                   @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir)
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

    @GetMapping("/add")
    public String AddNewNotePage(Model model)
    {
        // add note Page
        Note note = new Note();
        model.addAttribute("newNote",note);
        model.addAttribute("noteCategory",categoryService.getAllNoteCategories());
        return "notes/notes_add";
    }

    @PostMapping("/add-note")
    public  String AddNote(@Valid @ModelAttribute("newNote") Note newNote, Errors errors, Model model)
    {
        model.addAttribute("noteCategory",categoryService.getAllNoteCategories());
        if(errors.hasErrors())
        {
            return "notes/notes_add";
        }

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
        {
            return "notes/notes_update";
        }

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

}
