package nl.enovation.addressbook.jpa.repositories;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import nl.enovation.addressbook.jpa.contacts.Contact;
import nl.enovation.addressbook.jpa.contacts.PhoneNumberEntry;
import nl.enovation.addressbook.jpa.contacts.PhoneNumberType;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class PhoneNumberEntryRepository {

//    private Session session;
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Updates the PhoneNumberEntry in the database with the parameter PhoneNumberEntry.
     * 
     * @param phoneNumberEntry
     *            PhoneNumberEntry that is changed by user.
     */
    public PhoneNumberEntry save(PhoneNumberEntry phoneNumberEntry) {
        this.sessionFactory.getCurrentSession().save(phoneNumberEntry);
        return phoneNumberEntry;
    }

    /**
     * Retrieves one PhoneNumberEntry from the database using its identifier
     * 
     * @param identifier
     * @return phoneNumberEntry
     */
    public PhoneNumberEntry findOne(Long identifier) {
        return (PhoneNumberEntry) this.sessionFactory.getCurrentSession().get(PhoneNumberEntry.class, identifier);
    }

    /**
     * Get all contacts out of the database.
     * 
     * @return List<PhoneNumberEntries>
     *         List of all the PhoneNumberEntries that are stored in de database
     */
    @SuppressWarnings("unchecked")
    public List<PhoneNumberEntry> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("SELECT * FROM contact").list();
    }

    /**
     * Returns if the database is empty or not.
     * 
     * @return Boolean is true if the database is empty.
     */
    public boolean isEmpty() {
        return this.sessionFactory.getCurrentSession().createQuery("SELECT c FROM PhoneNumberEntry c").list().size() == 0;
    }

    /**
     * Removes a PhoneNumberEntry from the database.
     * 
     * @param phoneNumberEntry
     *            PhoneNumberEntry that is to be removed.
     * @return contact
     *         The deleted contact merged with any changes in the database.
     */
    public void delete(PhoneNumberEntry phoneNumberEntry) {
        this.sessionFactory.getCurrentSession().delete(phoneNumberEntry);
    }

    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
