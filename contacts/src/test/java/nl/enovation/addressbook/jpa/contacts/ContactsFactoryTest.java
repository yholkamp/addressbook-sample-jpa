package nl.enovation.addressbook.jpa.contacts;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ContactsFactoryTest {

    private Contact contactTest;

    private EntityManager contactsDatabase;

    private ContactsFactory cf;

    public void deleteAllRecords() {
        // clear the datbase.
        contactsDatabase.getTransaction().begin();
        Query query = contactsDatabase.createQuery("DELETE FROM Contact c");
        query.executeUpdate();
        contactsDatabase.getTransaction().commit();
    }

    @Before
    public void setup() {
        contactTest = new Contact();

        contactTest.setFirstName("Name");
        contactTest.setLastName("Last");
        contactTest.setPhoneNumber("061234");

        cf = new ContactsFactory();
        contactsDatabase = cf.getEntityManager();
    }

    @Test
    public void testAddContact() {
        // put object in database and check if its in the database
        assertFalse(contactsDatabase.contains(contactTest));
        cf.addContact(contactTest);
        assertTrue(contactsDatabase.contains(contactTest));
        this.deleteAllRecords();
    }

    @Test
    public void testEditContact() {
        // if testobject is changed and persisted to database. both should be the same.
        cf.addContact(contactTest);
        contactTest.setFirstName("Changed");
        cf.editContact(contactTest);
        assertEquals(contactTest, cf.getContact(contactTest.getIdentifier()));
    }

    @Test
    public void testGetContact() {
        // is object out of the database the same as what is put in
        cf.addContact(contactTest);
        // check
        assertEquals(contactTest, contactsDatabase.find(Contact.class, contactTest.getIdentifier()));
        this.deleteAllRecords();
    }

    @Test
    public void testGetContacts() {
        // check if all the items in database == getContacts();
        List<Contact> first = contactsDatabase.createQuery("SELECT c FROM Contact c").getResultList();
        List<Contact> second = cf.getContacts();
        assertEquals(first, second);
        this.deleteAllRecords();
    }

    @Test
    public void testIsEmpty() {
        // Database is empty right now
        assertTrue(cf.isEmpty());
        // fill database
        contactsDatabase.getTransaction().begin();
        contactsDatabase.persist(contactTest);
        contactsDatabase.getTransaction().commit();
        // database cannot be empty
        assertFalse(cf.isEmpty());
        this.deleteAllRecords();
    }

    @Test
    public void testRemoveContact() {
        // add testobject, when removed it should not be contained in de database
        cf.addContact(contactTest);
        assertTrue(contactsDatabase.contains(contactTest));
        cf.removeContact(contactTest);
        assertFalse(contactsDatabase.contains(contactTest));
        this.deleteAllRecords();
    }

    @Test
    public void testSearchForContact() {
        // firstName
        cf.addContact(contactTest);
        List<Contact> list = cf.searchForContacts(contactTest.getFirstName());
        assertTrue(list.contains(contactTest));
        list.clear();
        // lastName
        list = cf.searchForContacts(contactTest.getLastName());
        assertTrue(list.contains(contactTest));
        this.deleteAllRecords();
    }
}
