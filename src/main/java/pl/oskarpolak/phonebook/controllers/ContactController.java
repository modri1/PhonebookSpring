package pl.oskarpolak.phonebook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.oskarpolak.phonebook.models.UserSession;
import pl.oskarpolak.phonebook.models.forms.ContactForm;
import pl.oskarpolak.phonebook.models.services.ContactService;

@Controller
public class ContactController {

    final ContactService contactService;
    final UserSession userSession;

    @Autowired
    public ContactController(ContactService contactService, UserSession userSession) {
        this.contactService = contactService;
        this.userSession = userSession;
    }


    @GetMapping("/contact/add")
    public String showAddContactForm(Model model){
        model.addAttribute("contactForm", new ContactForm());
        return "addContact";
    }

    @PostMapping("/contact/add")
    public String getDataFromAddForm(@ModelAttribute ContactForm contactForm,
                                     Model model){
        if(contactService.checkIfContactExists(contactForm.getSurname())){
            model.addAttribute("isSurnameBusy", true);
            return "addContact";
        }
        contactService.addContact(contactForm);


        return "addContact"; //todo change after save data
    }

    @GetMapping("/contact/show")
    public String showAllContacts(Model model) {
        if(!userSession.isLogin()){
            return "redirect:/user/login";
        }
        model.addAttribute("contacts", userSession.getUserEntity().getContacts());
        return "contactsList";
    }

    @GetMapping("/contact/show/id/{id}")
    @ResponseBody
    public String showOneContact(@PathVariable("id") int contactId){
        return contactService
                .findOneContact(contactId)
                .map(s -> s.toString())
                .orElse("Contact with this id not exist");
    }


    @GetMapping("/contact/show/surname/{surname}")
    @ResponseBody
    public String showOneContactSurname(@PathVariable("surname") String surname){
        return contactService
                .findOneContact(surname)
                .map(s -> s.toString())
                .orElse("Contact with this surname not exist");

    }

    @GetMapping("/contact/delete/{contactId}")
    public String deleteContact(@PathVariable("contactId") int id){
        contactService.deleteContact(id);
        return "redirect:/contact/show";
    }




}
