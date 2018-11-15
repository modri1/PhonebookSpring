package pl.oskarpolak.phonebook.models.services;


import lombok.Data;

@Data
public class RegisterForm {
    private String login;
    private String password;
}
