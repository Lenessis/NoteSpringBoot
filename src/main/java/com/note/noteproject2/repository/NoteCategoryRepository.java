package com.note.noteproject2.repository;

import com.note.noteproject2.model.Note;
import com.note.noteproject2.model.NoteCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteCategoryRepository extends JpaRepository<NoteCategory,Long> {

    @Query(value="select * from categories c where c.name like %:keywords%", nativeQuery = true)
    List<NoteCategory> findByKeywords(@Param("keywords") String keywords);

}
