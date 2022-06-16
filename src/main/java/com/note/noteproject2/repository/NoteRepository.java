package com.note.noteproject2.repository;

import com.note.noteproject2.model.Note;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {

    /* -- ALL NOTES -- */

    @Query(value="select * from notes n where n.title like %:keywords% or n.description like %:keywords%", nativeQuery = true)
    List<Note> findByKeywords(@Param("keywords") String keywords);

    @Query(value="select * from notes n, categories c where n.categories=c.id and c.name = :categoryFilter", nativeQuery = true)
    List<Note> findByCategory(@Param("categoryFilter") String categoryFilter);


    /* --- OWNER --- */

    @Query(value = "select * from notes n where n.owner = :owner", nativeQuery = true)
    List<Note> findNotesByOwner(@Param("owner") String owner);

    /*@Query(value = "select n from notes n where n.owner = :owner", nativeQuery = true)
    List<Note> findNotesByOwner(@Param("owner") String owner, Sort sort);*/

    /* -- SORTING & FILTERING -- */

    @Query(value = "select * from notes n where n.owner = :owner order by n.title asc", nativeQuery = true)
    List<Note> findNotesByOwnerTitleASC(@Param("owner") String owner);

    @Query(value = "select * from notes n where n.owner = :owner order by n.title desc", nativeQuery = true)
    List<Note> findNotesByOwnerTitleDESC(@Param("owner") String owner);

    @Query(value = "select * from notes n where n.owner = :owner order by n.date_of_upload asc", nativeQuery = true)
    List<Note> findNotesByOwnerDateASC(@Param("owner") String owner);

    @Query(value = "select * from notes n where n.owner = :owner order by n.date_of_upload desc", nativeQuery = true)
    List<Note> findNotesByOwnerDateDESC(@Param("owner") String owner);

    /* -- SEARCHING -- */

    @Query(value = "select * from notes n where n.owner = :owner and (n.title like %:keywords% or n.description like %:keywords%)", nativeQuery = true)
    List<Note> findNotesByOwnerAndKeywords(@Param("owner") String owner, @Param("keywords") String keywords);

    @Query(value = "select * from notes n, categories c where (n.categories=c.id and c.name = :categoryFilter) and n.owner = :owner", nativeQuery = true)
    List<Note> findNotesByOwnerAndCategory(@Param("owner") String owner, @Param("categoryFilter") String categoryFilter);


    /* --- SHARED NOTES FOR EVERYONE --- */

    @Query(value = "select * from notes n where n.shared = 1", nativeQuery = true)
    List<Note> findPublicNotes();

    /*@Query("select n from notes n where n.shared = 1")
    List<Note> findPublicNotes(Sort sort);*/

    /* -- SORTING & FILTERING -- */

    @Query(value = "select * from notes n where n.shared = 1 order by n.title asc", nativeQuery = true)
    List<Note> findPublicNotesByTitleASC();

    @Query(value = "select * from notes n where n.shared = 1 order by n.title desc", nativeQuery = true)
    List<Note> findPublicNotesByTitleDESC();

    @Query(value = "select * from notes n where n.shared = 1 order by n.date_of_upload asc", nativeQuery = true)
    List<Note> findPublicNotesByDateASC();

    @Query(value = "select * from notes n where n.shared = 1 order by n.date_of_upload desc", nativeQuery = true)
    List<Note> findPublicNotesByDateDESC();


    @Query(value = "select * from notes n where n.shared = 1 and (n.title like %:keywords% or n.description like %:keywords%)", nativeQuery = true)
    List<Note> findPublicNotesByKeywords(@Param("keywords") String keywords);

    @Query(value = "select * from notes n, categories c where n.shared = 1 and n.categories=c.id and c.name = :categoryFilter", nativeQuery = true)
    List<Note> findPublicNotesByCategory(@Param("categoryFilter") String categoryFilter);

}
