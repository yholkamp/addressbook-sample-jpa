package nl.enovation.addressbook.jpa.repositories;

import java.util.List;

import nl.enovation.addressbook.jpa.contacts.Contact;
import nl.enovation.addressbook.jpa.contacts.PhoneNumberEntry;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PhoneNumberEntryRepository {

    private EntityManager phoneNumberEM;

    public PhoneNumberEntryRepository() {
        phoneNumberEM = Persistence.createEntityManagerFactory("addressbook").createEntityManager();
    }

    /**
     * Stores a PhoneNumberEntry in the database.
     * 
     * @param contact
     *            PhoneNumberEntry that is stored in the database
     */
    public void add(PhoneNumberEntry contact) {
        phoneNumberEM.getTransaction().begin();
        phoneNumberEM.persist(contact);
        phoneNumberEM.getTransaction().commit();
    }

    /**
     * Updates the PhoneNumberEntry in the database with the parameter PhoneNumberEntry.
     * 
     * @param phoneNumberEntry
     *            PhoneNumberEntry that is changed by user.
     */
    public PhoneNumberEntry save(PhoneNumberEntry phoneNumberEntry) {
        if (phoneNumberEntry.getIdentifier() == null) {
            phoneNumberEM.persist(phoneNumberEntry);
            return phoneNumberEntry;
        } else {
            return phoneNumberEM.merge(phoneNumberEntry);
        }
    }

    /**
     * Retrieves one PhoneNumberEntry from the database using its identifier
     * 
     * @param identifier
     * @return phoneNumberEntry
     */
    public PhoneNumberEntry findOne(Long identifier) {
        return phoneNumberEM.find(PhoneNumberEntry.class, identifier);
    }

    /**
     * Get all contacts out of the database.
     * 
     * @return List<PhoneNumberEntries>
     *         List of all the PhoneNumberEntries that are stored in de database
     */
    @SuppressWarnings("unchecked")
    public List<PhoneNumberEntry> findAll() {
        return phoneNumberEM.createQuery("SELECT c FROM PhoneNumberEntry c").getResultList();
    }

    /**
     * @return EntityManager used by PhoneNumberEntriesFactory
     */
    public EntityManager getEntityManager() {
        return phoneNumberEM;
    }

    /**
     * Returns if the database is empty or not.
     * 
     * @return Boolean is true if the database is empty.
     */
    public boolean isEmpty() {
        return phoneNumberEM.createQuery("SELECT c FROM PhoneNumberEntry c").getResultList().isEmpty();
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
        phoneNumberEM.getTransaction().begin();
        PhoneNumberEntry mergedPhoneNumberEntry = phoneNumberEM.merge(phoneNumberEntry);
        phoneNumberEM.remove(mergedPhoneNumberEntry);
        phoneNumberEM.getTransaction().commit();
    }
}
