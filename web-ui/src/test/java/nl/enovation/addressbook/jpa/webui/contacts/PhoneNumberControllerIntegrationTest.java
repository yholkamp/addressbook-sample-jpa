package nl.enovation.addressbook.jpa.webui.contacts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import nl.enovation.addressbook.jpa.contacts.Contact;
import nl.enovation.addressbook.jpa.contacts.PhoneNumberEntry;
import nl.enovation.addressbook.jpa.contacts.PhoneNumberType;
import nl.enovation.addressbook.jpa.repositories.ContactsRepository;
import nl.enovation.addressbook.jpa.repositories.PhoneNumberEntryRepository;

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

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:/META-INF/persistence.xml" })
public class PhoneNumberControllerIntegrationTest {
    @Mock
    private BindingResult mockBindingResult;

    @Autowired
    private PhoneNumberController controller;

    @Autowired
    private ContactsRepository contactsFactory;

    @Autowired
    private PhoneNumberEntryRepository phoneNumberRepository;
    
    @Mock
    private Model model;

    private Contact createContact() {
        Contact contact = new Contact();
        contact.setFirstName("Foo");
        contact.setLastName("Bar");
        return contact;
    }

    private PhoneNumberEntry createPhoneNumberEntry() {
        PhoneNumberEntry phoneNumber = new PhoneNumberEntry();
        phoneNumber.setPhoneNumber("+31123456789");
        phoneNumber.setPhoneNumberType(PhoneNumberType.WORK);
        return phoneNumber;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(mockBindingResult.hasErrors()).thenReturn(false);

        controller = new PhoneNumberController();
        contactsFactory = new ContactsRepository();
    }
    
    @Test
    public void testDeleteForm() {
        assertNotNull("PhoneNumberRepository should not be null", phoneNumberRepository);

        // Set up a contact
        Contact contact = createContact();
        contactsFactory.addContact(contact);
        assertEquals("Contact should be retrievable from repository", contact, contactsFactory.getContact(contact.getIdentifier()));

        // Set up a phone number for the contact
        PhoneNumberEntry phoneNumber = createPhoneNumberEntry();
        phoneNumber = phoneNumberRepository.save(phoneNumber);
        assertEquals("Contact should have the new phoneNumber set in the database", 1, contactsFactory.getContact(contact.getIdentifier())
                                                                                                                  .getPhoneNumbers().size());

        String view = controller.formDelete(contact.getIdentifier(), phoneNumber.getPhoneNumber(), model);

        // Check that we returned back to the contact list
        assertEquals("phonenumbers/delete", view);
    }

    @Test
    public void testDeletePhoneNumber() {
        // Set up a contact
        Contact contact = createContact();
        contactsFactory.addContact(contact);
        assertEquals("Contact should be retrievable from repository", contact, contactsFactory.getContact(contact.getIdentifier()));

        // Set up a phone number for the contact
        PhoneNumberEntry phoneNumber = createPhoneNumberEntry();
        assertEquals("Contact should have the new phoneNumber set in the database", 1, contactsFactory.getContact(contact.getIdentifier())
                                                                                                                  .getPhoneNumbers().size());

        String view = controller.formDelete(contact.getIdentifier(), phoneNumber, mockBindingResult);

        // Check that we returned back to the contact list
        assertEquals("redirect:/contacts/" + contact.getIdentifier(), view);

        Contact contactFromDb = contactsFactory.getContact(contact.getIdentifier());
        assertEquals("PhoneNumber should have been removed", new ArrayList<PhoneNumberEntry>(), contactFromDb.getPhoneNumbers());
    }
    
    @Test
    public void testNewPhoneNumberForm() {
        String view = controller.formNew(42L, model);

        // Check that we'reback to the original form
        assertEquals("phonenumbers/new", view);
    }

    @Test
    public void testNewPhoneNumber_failure() {
        Mockito.when(mockBindingResult.hasErrors()).thenReturn(true);
        PhoneNumberEntry phoneNumber = createPhoneNumberEntry();

        String view = controller.formNewSubmit(42L, phoneNumber, mockBindingResult);

        // Check that we'reback to the original form
        assertEquals("phonenumbers/new", view);
    }

    @Test
    public void testNewPhoneNumber_success() {
        Contact contact = createContact();
        contactsFactory.addContact(contact);
        assertEquals("Contact should be retrievable from repository", contact, contactsFactory.getContact(contact.getIdentifier()));

        PhoneNumberEntry phoneNumber = createPhoneNumberEntry();
        List<PhoneNumberEntry> phoneNumbers = contact.getPhoneNumbers();
        if (phoneNumbers == null) {
            phoneNumbers = new ArrayList<PhoneNumberEntry>();
        }
        phoneNumbers.add(phoneNumber);

        String view = controller.formNewSubmit(contact.getIdentifier(), phoneNumber, mockBindingResult);

        // Check that we're back to the overview
        assertEquals("redirect:/contacts/" + contact.getIdentifier(), view);

        Contact contactFromDb = contactsFactory.getContact(contact.getIdentifier());
        assertNotNull("Should be able to find our contact in the db", contactFromDb);
        assertEquals("PhoneNumber should have been added in the db", phoneNumbers, contactFromDb.getPhoneNumbers());
    }

}
