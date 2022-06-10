package com.note.noteproject2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name ="notes")
@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 20) //3-20 znaków
    @NotEmpty(message = "Nazwa nie powinna być pusta")
    @Size(min=3, max=20, message = "Nazwa notatki powinna mieć od 3 do 20 znaków")
    private String title;

    @Column(name = "description", nullable = false, length = 500) // 5-500
    @NotEmpty(message = "Opis nie powinien być pusty")
    @Size(min=5, max=500, message = "Opis notatki powinien mieć od 5 do 500 znaków")
    private String description;

    //link
    /*@NotEmpty(message = "Data nie może być pusta")
    private String dateOfUpload; // data dodania dd-mm-yyyy
    //private Date date = new Date();*/

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateOfUpload;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name="categories", nullable = false)
    private NoteCategory category;

    public Note()
    {
        dateOfUpload = new Date();
    }


}
