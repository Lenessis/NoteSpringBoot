package com.note.noteproject2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class StartController {

    public StartController() {}

    @GetMapping("/")
    public String ShowIndex(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", auth.getName());

        return "index";
    }

    @GetMapping("/authors")
    public String ShowAuthors()
    {
        return "additional/authors";
    }

    @GetMapping("/contact")
    public String ShowContact() { return "additional/contact"; }

}
