package nl.enovation.addressbook.jpa.webui.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import nl.enovation.addressbook.jpa.pojo.Contact;
import nl.enovation.addressbook.jpa.pojo.PhoneNumberEntry;
import nl.enovation.addressbook.jpa.pojo.PhoneNumberType;
import nl.enovation.addressbook.jpa.repository.ContactRepository;
import nl.enovation.addressbook.jpa.repository.PhoneNumberEntryRepository;

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
 * @author Yorick Holkamp
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring/applicationContext.xml" })
public class PhoneNumberControllerIntegrationTest {
    @Mock
    private BindingResult mockBindingResult;

    @Autowired
    private PhoneNumberController controller;

    @Autowired
    private PhoneNumberEntryRepository phoneNumberEntryRepository;

    @Autowired
    private ContactRepository contactRepository;

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

    }

    @Test
    public void testDeleteForm() {
        Contact contact = createContact();
        contactRepository.save(contact);

        // Set up a phone number for the contact
        PhoneNumberEntry phoneNumber = createPhoneNumberEntry();
        phoneNumber.setContact(contact);
        phoneNumberEntryRepository.save(phoneNumber);
        contact.getPhoneNumbers().add(phoneNumber);

        assertNotNull("phoneNumber should have an identifier", phoneNumber.getIdentifier());

        String view = controller.formDelete(contact.getIdentifier(), phoneNumber.getIdentifier(), model);

        // Check that we're shown the confirmation page
        assertEquals("phonenumbers/delete", view);
    }

    @Test
    public void testDeletePhoneNumber() {
        Contact contact = createContact();
        contactRepository.save(contact);

        // Set up a phone number for the contact
        PhoneNumberEntry phoneNumber = createPhoneNumberEntry();
        phoneNumber.setContact(contact);
        phoneNumberEntryRepository.save(phoneNumber);
        contact.getPhoneNumbers().add(phoneNumber);

        assertNotNull("PhoneNumber should have an identifier", phoneNumber.getIdentifier());

        PhoneNumberEntry phoneNumberEntryFromDb = phoneNumberEntryRepository.findOne(phoneNumber.getIdentifier());
        assertNotNull("PhoneNumber should be recoverable from DB", phoneNumberEntryFromDb);
        assertNotNull("Should have a contact when fetched from DB", phoneNumberEntryFromDb.getContact());

        assertEquals("Contact should have the new phoneNumber set in the database", 1, phoneNumberEntryFromDb.getContact().getPhoneNumbers().size());

        String view = controller.formDelete(contact.getIdentifier(), phoneNumber, mockBindingResult);

        // Check that we returned back to the contact list
        assertEquals("redirect:/contacts/" + contact.getIdentifier(), view);
        //TODO: Check removal properly without Hibernate struggling 
        //assertEquals("PhoneNumber should have been removed", 0, contact.getPhoneNumbers().size());
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
        contactRepository.save(contact);

        PhoneNumberEntry phoneNumber = createPhoneNumberEntry();
        String view = controller.formNewSubmit(contact.getIdentifier(), phoneNumber, mockBindingResult);
        // assertEquals("Contact should be retrievable from repository", contact.hashCode(), contactRepository.findOne(contact.getIdentifier()).hashCode());

        // Check that we're back to the overview
        assertEquals("redirect:/contacts/" + contact.getIdentifier(), view);

        Contact contactFromDb = contactRepository.findOne(contact.getIdentifier());
        assertNotNull("Should be able to find our contact in the db", contactFromDb);
        assertEquals("PhoneNumber should have been added in the db", 1, contactFromDb.getPhoneNumbers().size());
    }

}
