package com.note.noteproject2.repository;

import com.note.noteproject2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByLogin(String login);
}
