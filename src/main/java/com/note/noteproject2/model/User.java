package com.note.noteproject2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;


@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users", uniqueConstraints = @UniqueConstraint(columnNames = "login"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 20) // 3-20 pierwsza duża
    private String name;

    @Column(name = "surname", nullable = false, length = 50) //3-50 pierwsza duża
    private String surname;

    @Column(name = "login", nullable = false, length = 20) // 3-20 male litery
    private String login;

    @Column(name = "password", nullable = false) // 5+ znakow
    private String password;

    @Column(name = "age", nullable = false, length = 3) // min 18 lat
    private int age;

    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))

    private Collection< Role > roles;

    public User()
    {}

    public User(String name, String surname, String login, String password, int age, Collection < Role > roles) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.age = age;
        this.roles = roles;
    }
}
