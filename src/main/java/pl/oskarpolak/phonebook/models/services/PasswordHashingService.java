package pl.oskarpolak.phonebook.models.services;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;

@Service
public class PasswordHashingService {
//
//    private BCryptPasswordEncoder passwordEncoder;
//    public PasswordHashingService(){
//        passwordEncoder = new BCryptPasswordEncoder();
//    }


    public String hash(String password){
        return getPasswordEncoder().encode(password);
    }

    public boolean matches(String password, String hash){

        return getPasswordEncoder().matches(password, hash);
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
