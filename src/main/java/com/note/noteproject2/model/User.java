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
    /*@NotEmpty(message = "Imię nie może być puste")
    @Size(min=3, max=20, message = "Imię powinno mieć od 3 do 20 znaków")
    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Imię powinno sie zaczynac wielką literą")*/
    private String name;

    @Column(name = "surname", nullable = false, length = 50) //3-50 pierwsza duża
    /*@NotEmpty(message = "Nazwisko nie może być puste")
    @Size(min=3, max=50, message = "Nazwisko powinno mieć od 3 do 50 znaków")
    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Nazwisko powinno sie zaczynac wielką literą")*/
    private String surname;

    @Column(name = "login", nullable = false, length = 20) // 3-20 male litery
    /*@NotEmpty(message = "Login nie może być pusty")
    @Size(min=3, max=20, message = "Login powinien mieć od 3 do 20 znaków")
    @Pattern(regexp = "^[a-z0-9]+$", message = "Login powinien zawierać tylko małe litery")*/
    private String login;

    @Column(name = "password", nullable = false) // 5+ znakow
    /*@NotEmpty(message = "Hasło nie może być puste")
    @Size(min=5, message = "Hasło musi mieć co najmniej 5 znaków")*/
    private String password;

    @Column(name = "age", nullable = false, length = 3) // min 18 lat
    /*@Min(value = 18, message = "Musisz mieć skończone 18 lat")*/
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
