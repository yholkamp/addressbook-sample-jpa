/*
 * Copyright (c) 2010. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.axonframework.samples.trader.webui.contacts;

import java.util.List;

import javax.validation.Valid;

import org.axonframework.samples.trader.contacts.Contact;
import org.axonframework.samples.trader.contacts.ContactsFactory;
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
 * @author Jettro Coenradie
 */
@Controller
@RequestMapping("/contacts")
public class ContactsController {

    public static final String PERSISTENCE_UNIT = "addressbook";

    private ContactsFactory contactsFactory;

    private final static Logger logger = LoggerFactory.getLogger(ContactsController.class);

    public ContactsController() {
        contactsFactory = new ContactsFactory();
        logger.debug("Dispatching command with name : {Contactscontroller, intialise done}");
    }

    @RequestMapping(value = "{identifier}", method = RequestMethod.GET)
    public String details(@PathVariable Long identifier, Model model) {
        String name = contactsFactory.getContact(identifier).getFirstName();
        logger.debug("Dispatching command with name : {getContact(id)}");
        model.addAttribute("identifier", identifier);
        model.addAttribute("name", name);
        return "contacts/details";
    }

    @RequestMapping(value = "{identifier}/delete", method = RequestMethod.POST)
    public String formDelete(@ModelAttribute("contact") Contact contact, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            contactsFactory.removeContact(contact);
            logger.debug("Dispatching command with name : {removeContact(contact)}");
            return "redirect:/contacts";
        }
        return "contacts/delete";
    }

    @RequestMapping(value = "{identifier}/delete", method = RequestMethod.GET)
    public String formDelete(@PathVariable Long identifier, Model model) {
        Contact contact = contactsFactory.getContact(identifier);
        logger.debug("Dispatching command with name : {getContact(id)}");
        model.addAttribute("contact", contact);
        return "contacts/delete";
    }

    @RequestMapping(value = "{identifier}/edit", method = RequestMethod.GET)
    public String formEdit(@PathVariable Long identifier, Model model) {
        Contact contact = contactsFactory.getContact(identifier);
        logger.debug("Dispatching command with name : {getContact(id)}");
        if (contact == null) {
            throw new RuntimeException("contactRepository with ID " + identifier + " could not be found.");
        }
        model.addAttribute("contact", contact);
        return "contacts/edit";
    }

    @RequestMapping(value = "{identifier}/edit", method = RequestMethod.POST)
    public String formEditSubmit(@ModelAttribute("contact") @Valid Contact contact, BindingResult bindingResult) {
        logger.debug("Received form submit for contact with identifier {}", contact.getIdentifier());
        if (bindingResult.hasErrors()) {
            return "contacts/edit";
        }
        contactsFactory.editContact(contact);
        logger.debug("Dispatching command with name : {editContact(contact)}");
        return "redirect:/contacts";
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String formNew(Model model) {
        Contact attributeValue = new Contact();
        logger.debug("Dispatching command with name : {getContact(id)}");
        model.addAttribute("contact", attributeValue);
        return "contacts/new";
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String formNewSubmit(@Valid Contact contact, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "contacts/new";
        }
        contactsFactory.addContact(contact);
        logger.debug("Dispatching command with name : {persistContact(contact)}");
        return "redirect:/contacts";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        List<Contact> listContacts = contactsFactory.getContacts();
        logger.debug("Dispatching command with name : {getContacts}");
        model.addAttribute("contacts", listContacts);
        return "contacts/list";
    }
}