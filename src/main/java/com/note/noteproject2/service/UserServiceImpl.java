package com.note.noteproject2.service;

import com.note.noteproject2.model.Role;
import com.note.noteproject2.model.User;
import com.note.noteproject2.repository.UserRepository;
import com.note.noteproject2.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    private UserServiceImpl (UserRepository userRepository)
    {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto userDTO) {

        Role role = new Role("ROLE_USER"); //?

        User user = new User(userDTO.getName(),userDTO.getSurname(), userDTO.getLogin(), userDTO.getPassword(), userDTO.getAge(), role);

        return userRepository.save(user);
    }

    /*@Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }*/
}
