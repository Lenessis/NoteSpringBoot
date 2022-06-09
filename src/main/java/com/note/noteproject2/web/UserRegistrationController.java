package com.note.noteproject2.web;

import com.note.noteproject2.service.UserService;
import com.note.noteproject2.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    // -- Model Attribute User DTO
    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto()
    {
        return new UserRegistrationDto();
    }

    // --- Registration Form page
    @GetMapping
    public String showRegistrationPage()
    {
        return "auth/registration";
    }

    // --- Registration request
    @PostMapping
    public String registerUserAccount (@ModelAttribute("user")UserRegistrationDto userRegistrationDto)
    {

        userService.save(userRegistrationDto);
        return "redirect:/registration?success";
    }

}
