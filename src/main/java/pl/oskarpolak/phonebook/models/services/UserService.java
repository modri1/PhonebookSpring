package pl.oskarpolak.phonebook.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.oskarpolak.phonebook.models.LoginForm;
import pl.oskarpolak.phonebook.models.RegisterForm;
import pl.oskarpolak.phonebook.models.UserEntity;
import pl.oskarpolak.phonebook.models.repositories.UserRepository;

@Service
public class UserService {

    final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkIfLoginExists(String login){
        return userRepository.existsByLogin(login);
    }

    public void addUser(RegisterForm userForm){
        UserEntity newUser = new UserEntity();
        newUser.setLogin(userForm.getLogin());
        newUser.setPassword(userForm.getPassword());

        userRepository.save(newUser);
    }

    public boolean checkUserLoginData(LoginForm loginForm){
        return userRepository.existsByLoginAndPassword(loginForm.getLogin(), loginForm.getPassword());
    }
}
