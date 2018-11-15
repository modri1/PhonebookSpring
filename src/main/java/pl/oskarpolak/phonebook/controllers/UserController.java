package pl.oskarpolak.phonebook.controllers;


import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.oskarpolak.phonebook.models.services.RegisterForm;
import pl.oskarpolak.phonebook.models.services.UserService;

@Controller
public class UserController {

    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/add")
    public String showRegisterForm(Model model){
        model.addAttribute("registerForm", new RegisterForm());
        return "registerForm";
    }

    @PostMapping("/user/add")
    public String getDataFromRegister(@ModelAttribute RegisterForm registerForm,
                                      Model model){
        if(userService.checkIfLoginExists(registerForm.getLogin())) {
            model.addAttribute("registerInfo", "Login zajety");
            return "registerForm";
        }

        userService.addUser(registerForm);
        model.addAttribute("registerInfo", "Zarejestrowano poprawnie!");
        return "registerForm";
    }

}

