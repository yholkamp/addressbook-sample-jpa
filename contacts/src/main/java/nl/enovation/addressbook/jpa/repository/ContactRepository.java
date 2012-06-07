package nl.enovation.addressbook.jpa.repository;

import java.util.List;

import nl.enovation.addressbook.jpa.pojo.Contact;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository class providing access to Contacts in our database. Persistence further on is handled by Hibernate.
 * 
 * @author Maarten van Meijeren, Yorick Holkamp
 */
@Transactional
@Repository
public class ContactRepository {

    private SessionFactory sessionFactory;

    /**
     * Removes a Contact from the database.
     * 
     * @param contact
     *            Contact that is to be removed.
     * @return contact
     *         The deleted contact merged with any changes in the database.
     */
    public Contact delete(Contact contact) {
        sessionFactory.getCurrentSession().delete(contact);
        return contact;
    }

    /**
     * Get all contacts out of the database.
     * 
     * @return listContacts
     *         List of all the Contacts that are stored in de database
     */
    @SuppressWarnings("unchecked")
    public List<Contact> findAll() {
        return sessionFactory.getCurrentSession().createQuery("SELECT c FROM Contact c").list();
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
        Query jpqlQuery = sessionFactory.getCurrentSession()
                                        .createQuery("Select cnt from Contact cnt where cnt.firstName like :name OR cnt.lastName like :name");
        return jpqlQuery.setParameter("name", "%" + value + "%").list();
    }

    /**
     * Retrieves one specific contact from the database using its unique identifier.
     * 
     * @return contact
     *         Contact that was found in the database.
     */
    public Contact findOne(Long identifier) {
        Session session = sessionFactory.getCurrentSession();
        return (Contact) session.get(Contact.class, identifier);
    }

    /**
     * Flushes any changes made in the current this.sessionFactory.getCurrentSession().
     */
    public void flush() {
        sessionFactory.getCurrentSession().flush();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Returns if the database is empty or not.
     * 
     * @return Boolean is true if the database is empty.
     */
    public boolean isEmpty() {
        return sessionFactory.getCurrentSession().createQuery("SELECT c FROM Contact c").list().isEmpty();
    }

    /**
     * Updates the Contact in the database with the parameter Contact.
     * 
     * @param contact
     *            Contact that is changed by user.
     */
    public Contact save(Contact contact) {
        Session session = sessionFactory.getCurrentSession();
        if (contact.getIdentifier() == null) {
            session.save(contact);
            return contact;
        } else {
            return (Contact) session.merge(contact);
        }
    }

    /**
     * Sets the sessionFactory to the provided object.
     * 
     * @param sessionFactory
     */
    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
