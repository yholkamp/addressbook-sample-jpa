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

import java.util.Random;

import org.axonframework.samples.trader.webui.init.DBInit;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.validation.Valid;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.domain.StringAggregateIdentifier;
import org.axonframework.samples.trader.contacts.api.ChangeContactNameCommand;
import org.axonframework.samples.trader.contacts.api.CreateContactCommand;
import org.axonframework.samples.trader.contacts.api.RemoveContactCommand;
import org.axonframework.samples.trader.query.contacts.ContactEntry;
import org.axonframework.samples.trader.contacts.Contact;
import org.axonframework.samples.trader.query.contacts.repositories.ContactQueryRepository;
import org.axonframework.samples.trader.webui.init.DBInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	//
	// static {
	// DemoDataGenerator.create();
	// }
	//
	// @Override
	// public void init() {
	// Window window = new Window();
	// setMainWindow(window);
	// window.setContent(new AddressBookMainView());
	// }

	// private final static Logger logger =
	// LoggerFactory.getLogger(ContactsController.class);
	// private ContactQueryRepository contactRepository;
	// ContactQueryRepository contactRepository,
	// private CommandBus commandBus;
	// CommandBus commandBus

	// @Autowired
	public ContactsController() {
		System.out.println("CONTACTSCONTROLLER");
		// DBInit.createItems();

		EntityManager em = Persistence
				.createEntityManagerFactory("addressbook")
				.createEntityManager();

		System.out.println("EM CREATED!");

		em.getTransaction().begin();
		Contact p = new Contact();
		p.setFirstName("baz");
		p.setLastName("bar");
		p.setCity("fooooo");
		p.setPhoneNumber("+358 02 555 ");
		p.setZipCode("123");
		p.setStreet("foobar lane");
		p.setDepartment("Incompetence Inc.");

		System.out.println("Before PERSIST");
		em.persist(p);
		System.out.println("AFTER PERSIST");

		em.getTransaction().commit();

		// this.contactRepository = contactRepository;
		// this.commandBus = commandBus;
	}

	// @RequestMapping(method = RequestMethod.GET)
	// public String list(Model model) {
	// model.addAttribute("contacts", contactRepository.findAll());
	// return "contacts/list";
	// }

	// @RequestMapping(value = "{identifier}", method = RequestMethod.GET)
	// public String details(@PathVariable String identifier, Model model) {
	// String name = contactRepository.findOne(identifier).getName();
	// model.addAttribute("identifier", identifier);
	// model.addAttribute("name", name);
	// return "contacts/details";
	// }

	// @RequestMapping(value = "{identifier}/edit", method = RequestMethod.GET)
	// public String formEdit(@PathVariable String identifier, Model model) {
	// ContactEntry contact = contactRepository.findOne(identifier);
	// if(contact == null) {
	// throw new RuntimeException("contactRepository with ID " + identifier +
	// " could not be found.");
	// }
	// model.addAttribute("contact", contact);
	// return "contacts/edit";
	// }

	// @RequestMapping(value = "{identifier}/edit", method = RequestMethod.POST)
	// public String formEditSubmit(
	// @ModelAttribute("contact") @Valid ContactEntry contactEntry,
	// BindingResult bindingResult) {
	// // beware, we cannot support other updates since that would always give
	// // an error when the name is not changed
	// if (contactHasErrors(contactEntry, bindingResult)) {
	// return "contacts/edit";
	// }
	//
	// ChangeContactNameCommand command = new ChangeContactNameCommand();
	// command.setContactNewName(contactEntry.getName());
	// command.setContactId(new
	// StringAggregateIdentifier(contactEntry.getIdentifier()));
	//
	// commandBus.dispatch(command);
	// logger.debug("Dispatching command with name : {}", command.toString());
	//
	// return "redirect:/contacts";
	// }

	// @RequestMapping(value = "new", method = RequestMethod.GET)
	// public String formNew(Model model) {
	// ContactEntry attributeValue = new ContactEntry();
	// model.addAttribute("contact", attributeValue);
	// return "contacts/new";
	// }

	// /**
	// * If we submit a new contact, we want immediate feedback if the contact
	// * could be added. If it could not be added we want an error. Therefore we
	// * use the Future callback mechanism as provide by Axon.
	// *
	// * @param contact
	// * ContactEntry object that contains the entered data
	// * @param bindingResult
	// * BindingResult containing information about the binding of the
	// * form data to the ContactEntry
	// * @return String representing the name of the view to present.
	// */
	// @RequestMapping(value = "new", method = RequestMethod.POST)
	// public String formNewSubmit(
	// @ModelAttribute("contact") @Valid ContactEntry contact,
	// BindingResult bindingResult) {
	// if (contactHasErrors(contact, bindingResult)) {
	// return "contacts/new";
	// }
	//
	// CreateContactCommand command = new CreateContactCommand();
	// command.setNewContactName(contact.getName());
	//
	// commandBus.dispatch((command));
	// logger.debug("Dispatching command with name : {}", command.toString());
	//
	// return "redirect:/contacts";
	// }
	//
	// @RequestMapping(value = "{identifier}/delete", method =
	// RequestMethod.GET)
	// public String formDelete(@PathVariable String identifier, Model model) {
	// ContactEntry contactEntry = contactRepository.findOne(identifier);
	// model.addAttribute("contact", contactEntry);
	// return "contacts/delete";
	// }
	//
	// @RequestMapping(value = "{identifier}/delete", method =
	// RequestMethod.POST)
	// public String formDelete(@ModelAttribute("contact") ContactEntry contact,
	// BindingResult bindingResult) {
	// if (!bindingResult.hasErrors()) {
	// RemoveContactCommand command = new RemoveContactCommand();
	// command.setContactId(new
	// StringAggregateIdentifier(contact.getIdentifier()));
	// commandBus.dispatch((command));
	// logger.debug("Dispatching command with name : {}",
	// command.toString());
	//
	// return "redirect:/contacts";
	// }
	// return "contacts/delete";
	// }
	//
	// /**
	// * Checks if the entered data for a contact is valid and if the provided
	// * contact has not yet been taken.
	// *
	// * @param contact
	// * Contact to validate
	// * @param bindingResult
	// * BindingResult that can contain error and can be used to store
	// * additional errors
	// * @return true if the contact has errors, false otherwise
	// */
	// private boolean contactHasErrors(ContactEntry contact, BindingResult
	// bindingResult) {
	// // if (bindingResult.hasErrors() ||
	// // !contactNameRepository.vacantContactName(contact.getName())) {
	// // ObjectError error = new FieldError("contact", "name",
	// // "The provided name \'" + contact.getName() + "\' already exists");
	// // bindingResult.addError(error);
	// // return true;
	// // }
	// return false;
	// }
}