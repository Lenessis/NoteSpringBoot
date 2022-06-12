package com.note.noteproject2.repository;

import com.note.noteproject2.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long> {

    @Query(value = "select * from roles r where r.role = :name", nativeQuery = true)
    public Role findByName(String name);
}
