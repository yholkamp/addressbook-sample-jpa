package org.axonframework.samples.trader.contacts;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;


public class ContactsFactory {

	private EntityManager contactsDatabase;
	private List<Contact> listContacts;
	
	
	
	public ContactsFactory(){
		contactsDatabase = Persistence.createEntityManagerFactory("addressbook").createEntityManager();
	}
	
	/**
	 * @return Entitymanager used by ContactsFactory
	 */
	public EntityManager getEntityManager(){
		return this.contactsDatabase;
	}

	/**
	 * Get all contacts out of the database.
	 * @return listContacts List of all the Contacts that are stored in de database
	 */
	public List<Contact> getContacts(){
		return listContacts = contactsDatabase.createQuery("SELECT c FROM Contact c").getResultList();
	}

	public Contact getContact(Long identifier) {
		return contactsDatabase.find(Contact.class, identifier);
	}

	/**
	 * Stores a Contact in the database.
	 * @param contact Contact that is stored in the database
	 */
	public void addContact(Contact contact) {
		contactsDatabase.getTransaction().begin();
		contactsDatabase.persist(contact);
		contactsDatabase.getTransaction().commit();
	}
	/**
	 * Removes the Contact in the database.  
	 * @param contact Contact that is removed by user first get the right object, then remove.
	 */
	public void removeContact(Contact contact) {
		contactsDatabase.getTransaction().begin();
		contact = contactsDatabase.merge(contact);
		contactsDatabase.remove(contact);
		contactsDatabase.getTransaction().commit();		
	}
	/**
	 * Returns if the database is empty or not.
	 * @return Boolean is true if the database is empty.
	 */
	public boolean isEmpty(){
		return contactsDatabase.createQuery("SELECT c FROM Contact c").getResultList().isEmpty();
	}
	/**
	 * Updates the Contact in the database with the parameter Contact.
	 * @param contact Contact that is changed by user.
	 */
	public void editContact(Contact contact) {
		contactsDatabase.getTransaction().begin();
		contactsDatabase.merge(contact);
		contactsDatabase.getTransaction().commit();
	}
}
