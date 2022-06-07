package com.note.noteproject2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name ="notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 20)
    private String title;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    private String dateOfUpload;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name="categories", nullable = false)
    private NoteCategory category;

}
