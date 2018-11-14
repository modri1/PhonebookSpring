package pl.oskarpolak.phonebook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.oskarpolak.phonebook.models.ContactForm;
import pl.oskarpolak.phonebook.models.services.ContactService;

@Controller
public class ContactController {

    final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }


    @GetMapping("/contact/add")
    public String showAddContactForm(Model model){
        model.addAttribute("contactForm", new ContactForm());
        return "addContact";
    }

    @PostMapping("/contact/add")
    public String getDataFromAddForm(@ModelAttribute ContactForm contactForm){
        contactService.addContact(contactForm);
        return "addContact"; //todo change after save data
    }

    @GetMapping("/contact/show")
    @ResponseBody
    public String showAllContacts() {
        return contactService.getContactForms().toString();
    }
}
