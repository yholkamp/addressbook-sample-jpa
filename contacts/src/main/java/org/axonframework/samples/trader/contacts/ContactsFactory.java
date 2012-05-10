package org.axonframework.samples.trader.contacts;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ContactsFactory {

    private EntityManager contactsDatabase;

    public ContactsFactory() {
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
    public void editContact(Contact contact) {
        contactsDatabase.getTransaction().begin();
        contactsDatabase.merge(contact);
        contactsDatabase.getTransaction().commit();
    }

    public Contact getContact(Long identifier) {
        return contactsDatabase.find(Contact.class, identifier);
    }

    /**
     * Get all contacts out of the database.
     * 
     * @return listContacts List of all the Contacts that are stored in de database
     */
    public List<Contact> getContacts() {
        return contactsDatabase.createQuery("SELECT c FROM Contact c").getResultList();
    }

    /**
     * @return Entitymanager used by ContactsFactory
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
     * Removes the Contact in the database.
     * 
     * @param contact
     *            Contact that is removed by user first get the right object, then remove.
     */
    public void removeContact(Contact contact) {
        contactsDatabase.getTransaction().begin();
        contact = contactsDatabase.merge(contact);
        contactsDatabase.remove(contact);
        contactsDatabase.getTransaction().commit();
    }
    
    /**
     * Searches and return the names that contain the (sub)String value
     * 
     * @param value is searchValue, looking for firstName or lastName containing this value
     * @return list of contacts that contain the String value in firstName or lastName
     */
    public List<Contact> searchForContacts(String value) {
        Query jpqlQuery = contactsDatabase.createQuery("Select cnt from Contact cnt where cnt.firstName like :name OR cnt.lastName like :name");
        return jpqlQuery.setParameter("name", "%" + value + "%").getResultList();
        
    }
}
