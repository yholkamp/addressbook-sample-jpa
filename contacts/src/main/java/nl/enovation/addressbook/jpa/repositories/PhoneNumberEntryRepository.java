package nl.enovation.addressbook.jpa.repositories;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import nl.enovation.addressbook.jpa.contacts.Contact;
import nl.enovation.addressbook.jpa.contacts.HibernateUtil;
import nl.enovation.addressbook.jpa.contacts.PhoneNumberEntry;
import nl.enovation.addressbook.jpa.contacts.PhoneNumberType;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;

public class PhoneNumberEntryRepository {

//    private Session session;

    public PhoneNumberEntryRepository() {
//        session = HibernateUtil.getSessionFactory().openSession();
    }

    /**
     * Updates the PhoneNumberEntry in the database with the parameter PhoneNumberEntry.
     * 
     * @param phoneNumberEntry
     *            PhoneNumberEntry that is changed by user.
     */
    public PhoneNumberEntry save(PhoneNumberEntry phoneNumberEntry) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (phoneNumberEntry.getIdentifier() == null) {
            session.save(phoneNumberEntry);
            session.flush();
            return phoneNumberEntry;
        } else {
            session.save(phoneNumberEntry);;
            return phoneNumberEntry;
        }
    }

    /**
     * Retrieves one PhoneNumberEntry from the database using its identifier
     * 
     * @param identifier
     * @return phoneNumberEntry
     */
    public PhoneNumberEntry findOne(Long identifier) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (PhoneNumberEntry) session.load(PhoneNumberEntry.class, identifier);
    }

    /**
     * Get all contacts out of the database.
     * 
     * @return List<PhoneNumberEntries>
     *         List of all the PhoneNumberEntries that are stored in de database
     */
    @SuppressWarnings("unchecked")
    public List<PhoneNumberEntry> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("SELECT * FROM contact").list();
    }

    /**
     * Returns if the database is empty or not.
     * 
     * @return Boolean is true if the database is empty.
     */
    public boolean isEmpty() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("SELECT c FROM PhoneNumberEntry c").list().size() == 0;
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
        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
        phoneNumberEntry = (PhoneNumberEntry) session.merge(phoneNumberEntry);
        session.delete(phoneNumberEntry);
//        session.getTransaction().commit();
    }
}
