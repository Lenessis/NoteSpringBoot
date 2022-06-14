package com.note.noteproject2.controller;

import com.note.noteproject2.model.Role;
import com.note.noteproject2.model.User;
import com.note.noteproject2.service.RoleService;
import com.note.noteproject2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String AdminPanelPage(Model model)
    {
        model.addAttribute("users", this.userService.getAllUsers());
        return "admin/admin";
    }

    @GetMapping("/update/{id}")
    public String UpdateUserPage(@PathVariable ("id") long id, Model model)
    {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("ListRole", roleService.getAllRoles());

        return "admin/user_edit";
    }

    @PostMapping("/update-user")
    public String UpdateUser(@ModelAttribute("user") User user, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return "redirect:/admin?fail";
        else
        {
            userService.updateUser(user);
            return "redirect:/admin?successUpdate";
        }
    }

    @GetMapping("/delete/{id}")
    public String UpdateUserPage(@PathVariable ("id") long id)
    {
        userService.deleteUser(id);
        return "redirect:/admin?successDelete";
    }

}
