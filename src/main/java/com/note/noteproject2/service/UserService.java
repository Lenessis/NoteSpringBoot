package com.note.noteproject2.service;

import com.note.noteproject2.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();
}
