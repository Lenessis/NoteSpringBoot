package com.note.noteproject2.service;

import com.note.noteproject2.model.User;
import com.note.noteproject2.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    User getUserById(long id);
    void deleteUser(long id);
    User save(UserRegistrationDto user);
    void updateUser(User user);
}
