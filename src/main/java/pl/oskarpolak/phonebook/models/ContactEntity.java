package pl.oskarpolak.phonebook.models;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Table(name = "contact")
@Data
public class ContactEntity {
    @Id
    @GeneratedValue
    private  int id;

    private String name;
    private String surname;

    @Column(name = "phone")
    private String number;
}
