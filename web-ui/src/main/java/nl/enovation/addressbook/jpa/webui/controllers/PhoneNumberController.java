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

package nl.enovation.addressbook.jpa.webui.controllers;

import javax.annotation.Resource;
import javax.validation.Valid;

import nl.enovation.addressbook.jpa.repositories.ContactRepository;
import nl.enovation.addressbook.jpa.repositories.PhoneNumberEntryRepository;

import nl.enovation.addressbook.jpa.contacts.Contact;
import nl.enovation.addressbook.jpa.contacts.HibernateUtil;
import nl.enovation.addressbook.jpa.contacts.PhoneNumberEntry;
import nl.enovation.addressbook.jpa.contacts.PhoneNumberType;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
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
    private ContactRepository contactsRepository;

    private PhoneNumberEntryRepository phoneNumberRepository;

    private final static Logger logger = LoggerFactory.getLogger(PhoneNumberController.class);
    
    private Session session;
    
    public PhoneNumberController() {
        contactsRepository = new ContactRepository();
        phoneNumberRepository = new PhoneNumberEntryRepository();
        logger.debug("Dispatching command with name : {Contactscontroller, intialise done}");
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @RequestMapping(value = "{contactIdentifier}/phonenumbers/{identifier}/delete", method = RequestMethod.POST)
    public String formDelete(@PathVariable Long contactIdentifier, @ModelAttribute("phoneNumber") @Valid PhoneNumberEntry phoneNumber,
                             BindingResult bindingResult) {
        
        
        if (!bindingResult.hasErrors()) {
            Transaction tx = null;
            int pre_count;
            Contact contact;
            try {
                tx = session.beginTransaction();
                contact = contactsRepository.findOne(contactIdentifier);
                pre_count = contact.getPhoneNumberEntries().size();
                phoneNumber.setContact(null);
                contact.getPhoneNumberEntries().remove(phoneNumber);
                session.delete(phoneNumber);
                tx.commit();
            } catch (HibernateException he) {
                if (tx != null) tx.rollback();
                throw he;
            } finally {
                session.close();
            }
//            assert(pre_count > contact.getPhoneNumberEntries().size());
            return "redirect:/contacts/" + contactIdentifier;
        }

        return "phonenumbers/delete";
    }

    @RequestMapping(value = "{contactIdentifier}/phonenumbers/{identifier}/delete", method = RequestMethod.GET)
    public String formDelete(@PathVariable Long contactIdentifier, @PathVariable Long identifier, Model model) {
        Contact contact = contactsRepository.findOne(contactIdentifier);
        model.addAttribute("contact", contact);
        model.addAttribute("phoneNumber", phoneNumberRepository.findOne(identifier));

        return "phonenumbers/delete";
    }

    @RequestMapping(value = "{contactIdentifier}/phonenumbers/new", method = RequestMethod.GET)
    public String formNew(@PathVariable Long contactIdentifier, Model model) {
        Contact contact = contactsRepository.findOne(contactIdentifier);
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
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Contact contact = contactsRepository.findOne(contactIdentifier);
            phoneNumber.setContact(contact);
            contact.getPhoneNumberEntries().add(phoneNumber);
            session.save(phoneNumber);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            throw he;
        } finally {
            session.close();
        }

        return "redirect:/contacts/" + contactIdentifier;
    }
}