package com.note.noteproject2.controller;

import com.note.noteproject2.model.NoteCategory;
import com.note.noteproject2.service.NoteCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/categories")
public class NoteCategoryController {

    @Autowired
    NoteCategoryServiceImpl categoryService;

    @GetMapping
    public String ViewAllCategoryPage(Model model, String keywords)
    {
        if(keywords!=null)
            model.addAttribute("categories", this.categoryService.getNoteCategoriesByKeywords(keywords));
        else
            model.addAttribute("categories", this.categoryService.getAllNoteCategories("name","asc"));
        return "categories/categories";
    }

    @GetMapping("/")
    public String ViewAllCategoryPage(Model model, String keywords,
                                      @RequestParam ("sortField") String sortField,
                                      @RequestParam("sortDir") String sortDir)
    {
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseDir", sortDir.equals("asc")? "desc": "asc");

        if(sortField == null)
            sortField = "name";

        if(sortDir == null)
            sortDir = "asc";

        if(keywords!=null)
            model.addAttribute("categories", this.categoryService.getNoteCategoriesByKeywords(keywords));
        else
            model.addAttribute("categories", this.categoryService.getAllNoteCategories(sortField,sortDir));
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
    public String AddCategory(@Valid @ModelAttribute("category") NoteCategory category, Errors errors)
    {
        if (errors.hasErrors())
        {
            return "categories/categories_add";
        }
        else
        {
            this.categoryService.saveNoteCategory(category);
            return "redirect:/categories";
        }
    }

    @GetMapping("/update/{id}")
    public String UpdateCategoryPage(@PathVariable(value = "id") long id, Model model)
    {
           NoteCategory category = this.categoryService.getNoteCategoryById(id);
           model.addAttribute("category", category);
           return "categories/categories_update";
    }

    @PostMapping("/update-category")
    public String UpdateCategory(@Valid @ModelAttribute("category") NoteCategory category, Errors errors)
    {
        if (errors.hasErrors())
        {
            return "categories/categories_update";
        }
        else
        {
            this.categoryService.saveNoteCategory(category);
            return "redirect:/categories";
        }
    }

    @GetMapping("/delete/{id}")
    public String DeleteCategory(@PathVariable(value = "id") long id)
    {
        this.categoryService.deleteNoteCategory(id);
        return "redirect:/categories";
    }

}
