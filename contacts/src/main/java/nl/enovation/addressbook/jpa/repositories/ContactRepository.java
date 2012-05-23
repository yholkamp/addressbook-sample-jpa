package nl.enovation.addressbook.jpa.repositories;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nl.enovation.addressbook.jpa.contacts.Contact;

@Transactional
@Repository
public class ContactRepository {
    
    private final static Logger logger = LoggerFactory.getLogger(ContactRepository.class);

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Updates the Contact in the database with the parameter Contact.
     * 
     * @param contact
     *            Contact that is changed by user.
     */
    public Contact save(Contact contact) {
        Session session = this.sessionFactory.getCurrentSession();
        if (contact.getIdentifier() == null) {
            session.save(contact);
            return contact;
        } else {
            return (Contact) session.merge(contact);
        }
    }

    /**
     * Retrieves one specific contact from the database using its unique identifier.
     * 
     * @return contact
     *              Contact that was found in the database.
     */
    public Contact findOne(Long identifier) {
        Session session = this.sessionFactory.getCurrentSession();
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
        return this.sessionFactory.getCurrentSession().createQuery("SELECT c FROM Contact c").list();
    }

    /**
     * Returns if the database is empty or not.
     * 
     * @return Boolean is true if the database is empty.
     */
    public boolean isEmpty() {
        return this.sessionFactory.getCurrentSession().createQuery("SELECT c FROM Contact c").list().isEmpty();
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
        this.sessionFactory.getCurrentSession().getTransaction().begin();
        Contact mergedContact = (Contact) this.sessionFactory.getCurrentSession().merge(contact);
        this.sessionFactory.getCurrentSession().delete(mergedContact);
        this.sessionFactory.getCurrentSession().getTransaction().commit();
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
        Query jpqlQuery = this.sessionFactory.getCurrentSession()
                                             .createQuery("Select cnt from Contact cnt where cnt.firstName like :name OR cnt.lastName like :name");
        return jpqlQuery.setParameter("name", "%" + value + "%").list();
    }

    /**
     * Flushes any changes made in the current this.sessionFactory.getCurrentSession().
     */
    public void flush() {
        this.sessionFactory.getCurrentSession().flush();
    }

    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        logger.debug("setSessionFactory has ran");
        logger.debug("sessionFactory is {}", sessionFactory);
        assert(sessionFactory != null);
        this.sessionFactory = sessionFactory;
    }
}
