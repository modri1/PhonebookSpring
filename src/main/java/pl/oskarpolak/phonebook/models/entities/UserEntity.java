package pl.oskarpolak.phonebook.models.entities;

import lombok.Data;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class UserEntity {
    private @Id @GeneratedValue int id;
    private String login;
    private String password;
    private @Column(name = "creation_time") LocalDateTime creationTime;

    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ContactEntity> contacts;



    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }
}
