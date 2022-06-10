package com.note.noteproject2.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {

    @NotEmpty(message = "Imię nie może być puste")
    @Size(min=3, max=20, message = "Imię powinno mieć od 3 do 20 znaków")
    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Imię powinno sie zaczynac wielką literą")
    private String name;

    @NotEmpty(message = "Nazwisko nie może być puste")
    @Size(min=3, max=50, message = "Nazwisko powinno mieć od 3 do 50 znaków")
    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Nazwisko powinno sie zaczynac wielką literą")
    private String surname;

    @NotEmpty(message = "Login nie może być pusty")
    @Size(min=3, max=20, message = "Login powinien mieć od 3 do 20 znaków")
    @Pattern(regexp = "^[a-z0-9]+$", message = "Login powinien zawierać tylko małe litery")
    private String login;

    @NotEmpty(message = "Hasło nie może być puste")
    @Size(min=5, message = "Hasło musi mieć co najmniej 5 znaków")
    private String password;

    @Min(value = 18, message = "Musisz mieć skończone 18 lat")
    private int age;

}
