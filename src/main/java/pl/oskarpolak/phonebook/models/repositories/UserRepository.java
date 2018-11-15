package pl.oskarpolak.phonebook.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.oskarpolak.phonebook.models.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    boolean existsByLoginAndPassword(String login, String password);
    boolean existsByLogin(String login);
}
