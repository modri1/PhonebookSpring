package pl.oskarpolak.phonebook.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.oskarpolak.phonebook.models.entities.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    boolean existsByLoginAndPassword(String login, String password);
    boolean existsByLogin(String login);


    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE login = ?1")
    Optional<UserEntity> getUserByLogin(String login);
}


