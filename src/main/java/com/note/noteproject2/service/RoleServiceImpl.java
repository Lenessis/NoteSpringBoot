package com.note.noteproject2.service;

import com.note.noteproject2.model.Role;
import com.note.noteproject2.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
