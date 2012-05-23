package nl.enovation.addressbook.jpa.webui.controllers;

import javax.validation.Valid;

import nl.enovation.addressbook.jpa.contacts.Contact;
import nl.enovation.addressbook.jpa.contacts.PhoneNumberEntry;
import nl.enovation.addressbook.jpa.contacts.PhoneNumberType;
import nl.enovation.addressbook.jpa.repositories.ContactRepository;
import nl.enovation.addressbook.jpa.repositories.PhoneNumberEntryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Yorick Holkamp
 */
@Controller
@RequestMapping("/contacts")
public class PhoneNumberController {
    private ContactRepository contactRepository;

    private PhoneNumberEntryRepository phoneNumberEntryRepository;

    private final static Logger logger = LoggerFactory.getLogger(PhoneNumberController.class);

    public PhoneNumberController() {
        logger.debug("Dispatching command with name : {Contactscontroller, intialise done}");
    }

    @RequestMapping(value = "{contactIdentifier}/phonenumbers/{identifier}/delete", method = RequestMethod.GET)
    public String formDelete(@PathVariable Long contactIdentifier, @PathVariable Long identifier, Model model) {
        Contact contact = contactRepository.findOne(contactIdentifier);
        model.addAttribute("contact", contact);
        model.addAttribute("phoneNumber", phoneNumberEntryRepository.findOne(identifier));

        return "phonenumbers/delete";
    }

    @RequestMapping(value = "{contactIdentifier}/phonenumbers/{identifier}/delete", method = RequestMethod.POST)
    public String formDelete(@PathVariable Long contactIdentifier, @ModelAttribute("phoneNumber") @Valid PhoneNumberEntry phoneNumber,
                             BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            phoneNumberEntryRepository.delete(phoneNumber);
            return "redirect:/contacts/" + contactIdentifier;
        }

        return "phonenumbers/delete";
    }

    @RequestMapping(value = "{contactIdentifier}/phonenumbers/new", method = RequestMethod.GET)
    public String formNew(@PathVariable Long contactIdentifier, Model model) {
        Contact contact = contactRepository.findOne(contactIdentifier);
        model.addAttribute("contact", contact);
        model.addAttribute("phoneNumberEntry", new PhoneNumberEntry());
        model.addAttribute(PhoneNumberType.values());
        return "phonenumbers/new";
    }

    @RequestMapping(value = "{contactIdentifier}/phonenumbers/new", method = RequestMethod.POST)
    public String formNewSubmit(@PathVariable Long contactIdentifier, @Valid PhoneNumberEntry phoneNumber, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "phonenumbers/new";
        }

        Contact contact = contactRepository.findOne(contactIdentifier);
        phoneNumber.setContact(contact);
        contact.getPhoneNumberEntries().add(phoneNumber);
        phoneNumberEntryRepository.save(phoneNumber);

        return "redirect:/contacts/" + contactIdentifier;
    }

    public void setContactRepository(ContactRepository contactsRepository) {
        contactRepository = contactsRepository;
    }

    public void setPhoneNumberEntryRepository(PhoneNumberEntryRepository phoneNumberRepository) {
        phoneNumberEntryRepository = phoneNumberRepository;
    }
}