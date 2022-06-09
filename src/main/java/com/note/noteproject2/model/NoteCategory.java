package com.note.noteproject2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="categories")
public class NoteCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 20) // 3-20 male litery
    @NotEmpty(message = "Nazwa nie powinna być pusta")
    @Size(min=3, max=20, message = "Nazwa kategorii powinna mieć od 3 do 20 znaków")
    @Pattern(regexp = "^[a-z]+$", message = "Nazwa kategorii powinna zawierać tylko małe litery")
    private String name;
}