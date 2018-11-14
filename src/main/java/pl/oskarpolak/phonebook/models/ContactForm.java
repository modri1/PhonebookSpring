package pl.oskarpolak.phonebook.models;


import lombok.Data;

@Data
public class ContactForm {
    private String name;
    private String surname;
    private String phone;
}
