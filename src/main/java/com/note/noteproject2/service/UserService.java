package com.note.noteproject2.service;

import com.note.noteproject2.model.User;
import com.note.noteproject2.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    //List<User> getAllUsers();
    User save(UserRegistrationDto user);
}
