package com.note.noteproject2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class StartController {

    public StartController() {}

    @GetMapping("/")
    public String ShowIndex()
    {
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
