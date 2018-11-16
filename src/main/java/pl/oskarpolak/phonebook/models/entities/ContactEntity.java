package pl.oskarpolak.phonebook.models.entities;

import lombok.Data;
import org.hibernate.annotations.JoinColumnOrFormula;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
