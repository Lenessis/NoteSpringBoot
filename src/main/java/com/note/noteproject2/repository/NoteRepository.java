package com.note.noteproject2.repository;

import com.note.noteproject2.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {

    @Query(value="select * from notes n where n.title like %:keywords% or n.description like %:keywords%", nativeQuery = true)
    List<Note> findByKeywords(@Param("keywords") String keywords);
}
