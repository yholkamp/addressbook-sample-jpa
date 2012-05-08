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
		
	public EntityManager getEntityManager(){
		return this.contactsDatabase;
	}
	
	public List<Contact> getContacts(){
		return listContacts = contactsDatabase.createQuery("SELECT c FROM Contact c").getResultList();
	}

	public Contact getContact(Long identifier) {
		return contactsDatabase.find(Contact.class, identifier);
	}

	public void addContact(Contact contact) {
		contactsDatabase.getTransaction().begin();
		contactsDatabase.persist(contact);
		contactsDatabase.getTransaction().commit();
	}

	public void removeContact(Contact contact) {
		contactsDatabase.getTransaction().begin();
//		Contact changeContact = contactsDatabase.find(Contact.class, contact.getIdentifier());
		contact = contactsDatabase.merge(contact);
		contactsDatabase.remove(contact);
		contactsDatabase.getTransaction().commit();		
	}
	
	public boolean isEmpty(){
		return contactsDatabase.createQuery("SELECT c FROM Contact c").getResultList().isEmpty();
	}

	public void editContact(Contact contact) {
		contactsDatabase.getTransaction().begin();
		contactsDatabase.merge(contact);
		contactsDatabase.getTransaction().commit();
	}
}
