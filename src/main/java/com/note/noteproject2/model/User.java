package com.note.noteproject2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Column(name = "login", nullable = false, length = 20)
    private String login;

    @Column(name = "password", nullable = false, length = 30)
    private String password;

    @Column(name = "age", nullable = false, length = 3)
    private int age;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name="roles", nullable = false)
    private Role role;

}
