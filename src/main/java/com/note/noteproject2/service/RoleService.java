package com.note.noteproject2.service;

import com.note.noteproject2.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    List<Role> getAllRoles();
}
