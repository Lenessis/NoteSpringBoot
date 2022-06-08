package com.note.noteproject2.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegistrationDto {

    private String name;
    private String surname;
    private String login;
    private String password;
    private int age;

}
