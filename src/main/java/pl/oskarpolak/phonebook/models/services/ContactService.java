package pl.oskarpolak.phonebook.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.oskarpolak.phonebook.models.ContactEntity;
import pl.oskarpolak.phonebook.models.ContactForm;
import pl.oskarpolak.phonebook.models.repositories.ContactRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    public void addContact(ContactForm contactForm){
        ContactEntity newContact = new ContactEntity();
        newContact.setName(contactForm.getName());
        newContact.setSurname(contactForm.getSurname());
        newContact.setNumber(contactForm.getPhone());


       contactRepository.save(newContact);
    }

    public Optional<ContactEntity> findOneContact(int id){
        return contactRepository.findById(id);
    }

    public Optional<ContactEntity> findOneContact(String surname){
        return contactRepository.findContactBySurname(surname);
    }

    public List<ContactEntity> getContacts(){
        return contactRepository.findAllContacts();
    }
}
