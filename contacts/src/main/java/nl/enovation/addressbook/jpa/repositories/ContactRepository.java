package nl.enovation.addressbook.jpa.repositories;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import nl.enovation.addressbook.jpa.contacts.Contact;
import nl.enovation.addressbook.jpa.contacts.HibernateUtil;

public class ContactRepository {

    private Session session;

    public ContactRepository() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    /**
     * Updates the Contact in the database with the parameter Contact.
     * 
     * @param contact
     *            Contact that is changed by user.
     */
    public Contact save(Contact contact) {
        if (contact.getIdentifier() == null) {
            session.getTransaction().begin();
            session.persist(contact);
            session.getTransaction().commit();
            return contact;
        } else {
            return (Contact) session.merge(contact);
        }
    }

    public Contact findOne(Long identifier) {
        return (Contact) session.get(Contact.class, identifier);
    }

    /**
     * Get all contacts out of the database.
     * 
     * @return listContacts
     *         List of all the Contacts that are stored in de database
     */
    @SuppressWarnings("unchecked")
    public List<Contact> findAll() {
        return session.createQuery("SELECT c FROM Contact c").list();
    }

    /**
     * Returns if the database is empty or not.
     * 
     * @return Boolean is true if the database is empty.
     */
    public boolean isEmpty() {
        return session.createQuery("SELECT c FROM Contact c").list().isEmpty();
    }

    /**
     * Removes a Contact from the database.
     * 
     * @param contact
     *            Contact that is to be removed.
     * @return contact
     *         The deleted contact merged with any changes in the database.
     */
    public Contact delete(Contact contact) {
        session.getTransaction().begin();
        Contact mergedContact = (Contact) session.merge(contact);
        session.delete(mergedContact);
        session.getTransaction().commit();
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
    public List<Contact> findByName(String value) {
        Query jpqlQuery = session.createQuery("Select cnt from Contact cnt where cnt.firstName like :name OR cnt.lastName like :name");
        return jpqlQuery.setParameter("name", "%" + value + "%").list();
    }

    /**
     * Flushes any changes made in the current session.
     */
    public void flush() {
        session.flush();
    }
}
