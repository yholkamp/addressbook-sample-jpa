package nl.enovation.addressbook.jpa.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import nl.enovation.addressbook.jpa.contacts.Contact;

public class ContactsRepository {

    private EntityManager contactsDatabase;

    public ContactsRepository() {
        contactsDatabase = Persistence.createEntityManagerFactory("addressbook").createEntityManager();
    }

    /**
     * Stores a Contact in the database.
     * 
     * @param contact
     *            Contact that is stored in the database
     */
    public void addContact(Contact contact) {
        contactsDatabase.getTransaction().begin();
        contactsDatabase.persist(contact);
        contactsDatabase.getTransaction().commit();
    }

    /**
     * Updates the Contact in the database with the parameter Contact.
     * 
     * @param contact
     *            Contact that is changed by user.
     */
    public Contact editContact(Contact contact) {
        contactsDatabase.getTransaction().begin();
        Contact contactFromDb = contactsDatabase.merge(contact);
        contactsDatabase.getTransaction().commit();
        return contactFromDb;
    }

    public Contact getContact(Long identifier) {
        return contactsDatabase.find(Contact.class, identifier);
    }

    /**
     * Get all contacts out of the database.
     * 
     * @return listContacts
     *         List of all the Contacts that are stored in de database
     */
    @SuppressWarnings("unchecked")
    public List<Contact> getContacts() {
        return contactsDatabase.createQuery("SELECT c FROM Contact c").getResultList();
    }

    /**
     * @return EntityManager used by ContactsFactory
     */
    public EntityManager getEntityManager() {
        return contactsDatabase;
    }

    /**
     * Returns if the database is empty or not.
     * 
     * @return Boolean is true if the database is empty.
     */
    public boolean isEmpty() {
        return contactsDatabase.createQuery("SELECT c FROM Contact c").getResultList().isEmpty();
    }

    /**
     * Removes a Contact from the database.
     * 
     * @param contact
     *            Contact that is to be removed.
     * @return contact
     *         The deleted contact merged with any changes in the database.
     */
    public Contact removeContact(Contact contact) {
        contactsDatabase.getTransaction().begin();
        Contact mergedContact = contactsDatabase.merge(contact);
        contactsDatabase.remove(mergedContact);
        contactsDatabase.getTransaction().commit();
        return mergedContact;
    }

    /**
     * Searches and return the names that contain the (sub)String value
     * 
     * @param value
     *            is searchValue, looking for firstName or lastName containing this value
     * @return list of contacts that contain the String value in firstName or lastName
     */
    @SuppressWarnings("unchecked")
    public List<Contact> searchForContacts(String value) {
        Query jpqlQuery = contactsDatabase.createQuery("Select cnt from Contact cnt where cnt.firstName like :name OR cnt.lastName like :name");
        return jpqlQuery.setParameter("name", "%" + value + "%").getResultList();

    }
}
