package pl.oskarpolak.phonebook.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.oskarpolak.phonebook.models.UserSession;
import pl.oskarpolak.phonebook.models.forms.LoginForm;
import pl.oskarpolak.phonebook.models.forms.RegisterForm;
import pl.oskarpolak.phonebook.models.entities.UserEntity;
import pl.oskarpolak.phonebook.models.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;
    final UserSession userSession;
    final PasswordHashingService passwordHashingService;

    @Autowired
    public UserService(UserRepository userRepository, UserSession userSession, PasswordHashingService passwordHashingService) {
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.passwordHashingService = passwordHashingService;
    }

    public boolean checkIfLoginExists(String login){
        return userRepository.existsByLogin(login);
    }

    public void addUser(RegisterForm userForm){
        UserEntity newUser = new UserEntity();
        newUser.setLogin(userForm.getLogin());
        newUser.setPassword(passwordHashingService.hash(userForm.getPassword()));

        userRepository.save(newUser);
    }

    public boolean tryLogin(LoginForm loginForm){
        Optional<UserEntity> userOptional =
                userRepository.getUserByLogin(loginForm.getLogin());
        if(userOptional.isPresent()){
            if(passwordHashingService.matches(loginForm.getPassword(),
                    userOptional.get().getPassword())) {

                userSession.setLogin(true);
                userSession.setUserEntity(userOptional.get());
            }
        }
        return userSession.isLogin();
    }
}
