package com.note.noteproject2.controller;

import com.note.noteproject2.model.NoteCategory;
import com.note.noteproject2.service.NoteCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class NoteCategoryController {

    @Autowired
    NoteCategoryServiceImpl categoryService;

    @GetMapping
    public String ViewAllCategoryPage(Model model)
    {
        model.addAttribute("categories", this.categoryService.getAllNoteCategories());
        return "categories/categories";
    }

    @GetMapping("/add")
    public String AddCategoryPage(Model model)
    {
        NoteCategory noteCategory = new NoteCategory();
        model.addAttribute("category", noteCategory);
        return "categories/categories_add";
    }

    @PostMapping("/add-category")
    public String AddCategory(@ModelAttribute("category") NoteCategory category)
    {
        this.categoryService.saveNoteCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/update/{id}")
    public String UpdateCategoryPage(@PathVariable(value = "id") long id, Model model)
    {
           NoteCategory category = this.categoryService.getNoteCategoryById(id);
           model.addAttribute("category", category);
           return "categories/categories_update";
    }

    @GetMapping("/delete/{id}")
    public String DeleteCategory(@PathVariable(value = "id") long id)
    {
        this.categoryService.deleteNoteCategory(id);
        return "redirect:/categories";
    }

}