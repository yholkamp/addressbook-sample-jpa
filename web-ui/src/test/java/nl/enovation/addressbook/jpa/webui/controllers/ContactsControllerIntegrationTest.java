package nl.enovation.addressbook.jpa.webui.controllers;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import nl.enovation.addressbook.jpa.contacts.Contact;
import nl.enovation.addressbook.jpa.repositories.ContactRepository;
import nl.enovation.addressbook.jpa.webui.controllers.ContactsController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 * @author Maarten van Meijeren
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring/applicationContext.xml" })
public class ContactsControllerIntegrationTest {
    @Mock
    private BindingResult mockBindingResult;

    @Autowired
    private ContactsController controller;

    @Mock
    private Model model;

    @Autowired
    private ContactRepository contactRepository;

    private Contact createContact() {
        Contact contact = new Contact();
        contact.setFirstName("Foo");
        contact.setLastName("Bar");
        return contact;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(mockBindingResult.hasErrors()).thenReturn(false);
    }

    @Test
    public void testDeleteCommand_failure() {
        Mockito.when(mockBindingResult.hasErrors()).thenReturn(true);

        Contact contact = createContact();
        contactRepository.save(contact);
        assertNotNull("Contact should receive an ID", contact.getIdentifier());

        String view = controller.formDelete(contact, mockBindingResult);

        // Check that we're shown the delete page again
        assertEquals("contacts/delete", view);
    }

    @Test
    public void testDeleteCommand_success() {
        Contact contact = createContact();
        contactRepository.save(contact);
        assertNotNull("Contact should receive an ID", contact.getIdentifier());

        assertEquals("Contact should be retrievable from repository", contact, contactRepository.findOne(contact.getIdentifier()));

        String view = controller.formDelete(contact, mockBindingResult);
        // Check that we returned back to the contact list
        assertEquals("redirect:/contacts", view);

         assertEquals("Contact should have been removed", null, contactRepository.findOne(contact.getIdentifier()));
    }

    @Test
    public void testDeleteForm() {
        Contact contact = createContact();
        contactRepository.save(contact);
        assertNotNull("Contact should receive an ID", contact.getIdentifier());

        String view = controller.formDelete(contact.getIdentifier(), model);

        // Check that we're shown the delete page again
        assertEquals("contacts/delete", view);
    }

    @Test
    public void testDetails() {
        Contact contact = createContact();
        contactRepository.save(contact);
        assertEquals("Contact should be retrievable from repository", contact, contactRepository.findOne(contact.getIdentifier()));

        String view = controller.details(contact.getIdentifier(), model);

        verify(model).addAttribute(eq("contact"), anyObject());

        // Check that we're shown the contact list view
        assertEquals("contacts/details", view);
    }

    @Test
    public void testListContacts() {
        String view = controller.list(model);

        verify(model).addAttribute(eq("contacts"), anyList());

        // Check that we're shown the contact list view
        assertEquals("contacts/list", view);
    }

    @Test
    public void testNew() {
        String view = controller.formNew(model);

        // Check that we're shown the contact list view
        assertEquals("contacts/new", view);
    }

    @Test
    public void testNewCommand_failure() {
        Mockito.when(mockBindingResult.hasErrors()).thenReturn(true);

        String view = controller.formNewSubmit(createContact(), mockBindingResult);

        // Check that we'reback to the original form
        assertEquals("contacts/new", view);
    }

    @Test
    public void testNewCommand_success() {
        Contact contact = createContact();
        String view = controller.formNewSubmit(contact, mockBindingResult);

        Contact contactFromDb = contactRepository.findOne(contact.getIdentifier());
        assertEquals(contact.hashCode(), contactFromDb.hashCode());

        // Check that we're sent to the contact detail page
        assertEquals("redirect:/contacts/"+contact.getIdentifier(), view);
    }

    @Test
    public void testUpdateCommand_failure() {
        Contact contact = createContact();
        Mockito.when(mockBindingResult.hasErrors()).thenReturn(true);

        contactRepository.save(contact);
        assertEquals("Contact should be retrievable from repository", contact, contactRepository.findOne(contact.getIdentifier()));

        String view = controller.formEditSubmit(contact, mockBindingResult);

        // Check that we'reback to the original form
        assertEquals("contacts/edit", view);
    }

    @Test
    public void testUpdateCommand_success() {
        Contact contact = createContact();
        contactRepository.save(contact);

        // contactEntry = contactQueryRepository.save(contactEntry);
        assertEquals("Contact should be retrievable from repository", contact, contactRepository.findOne(contact.getIdentifier()));

        contact.setFirstName("changedFirstName");
        String view = controller.formEditSubmit(contact, mockBindingResult);

        Contact contactFromDb = contactRepository.findOne(contact.getIdentifier());
        assertEquals("changedFirstName", contactFromDb.getFirstName());

        // Check that we're back to the overview
        assertEquals("redirect:/contacts/" + contact.getIdentifier(), view);
    }

    @Test
    public void testUpdateForm() {
        Contact contact = createContact();
        contactRepository.save(contact);

        assertEquals("Contact should be retrievable from repository", contact, contactRepository.findOne(contact.getIdentifier()));

        String view = controller.formEdit(contact.getIdentifier(), model);

        // Check that we'reback to the original form
        assertEquals("contacts/edit", view);
    }
}
