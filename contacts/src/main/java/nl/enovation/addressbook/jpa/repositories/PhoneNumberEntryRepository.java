package nl.enovation.addressbook.jpa.repositories;

import java.util.List;

import nl.enovation.addressbook.jpa.contacts.PhoneNumberEntry;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class PhoneNumberEntryRepository {

    private SessionFactory sessionFactory;

    /**
     * Removes a PhoneNumberEntry from the database.
     * 
     * @param phoneNumberEntry
     *            PhoneNumberEntry that is to be removed.
     * @return contact
     *         The deleted contact merged with any changes in the database.
     */
    public void delete(PhoneNumberEntry phoneNumberEntry) {
        sessionFactory.getCurrentSession().delete(phoneNumberEntry);
    }

    /**
     * Get all contacts out of the database.
     * 
     * @return List<PhoneNumberEntries>
     *         List of all the PhoneNumberEntries that are stored in de database
     */
    @SuppressWarnings("unchecked")
    public List<PhoneNumberEntry> findAll() {
        return sessionFactory.getCurrentSession().createQuery("SELECT * FROM contact").list();
    }

    /**
     * Retrieves one PhoneNumberEntry from the database using its identifier
     * 
     * @param identifier
     * @return phoneNumberEntry
     */
    public PhoneNumberEntry findOne(Long identifier) {
        return (PhoneNumberEntry) sessionFactory.getCurrentSession().get(PhoneNumberEntry.class, identifier);
    }

    /**
     * Returns if the database is empty or not.
     * 
     * @return Boolean is true if the database is empty.
     */
    public boolean isEmpty() {
        return sessionFactory.getCurrentSession().createQuery("SELECT c FROM PhoneNumberEntry c").list().size() == 0;
    }

    /**
     * Updates the PhoneNumberEntry in the database with the parameter PhoneNumberEntry.
     * 
     * @param phoneNumberEntry
     *            PhoneNumberEntry that is changed by user.
     */
    public PhoneNumberEntry save(PhoneNumberEntry phoneNumberEntry) {
        sessionFactory.getCurrentSession().save(phoneNumberEntry);
        return phoneNumberEntry;
    }

    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
