package nl.enovation.addressbook.jpa.contacts;

import java.util.Set;

import nl.enovation.addressbook.jpa.repositories.ContactRepository;
import nl.enovation.addressbook.jpa.repositories.PhoneNumberEntryRepository;

import org.hibernate.Session;

public class Test {

    public static void main(String[] args) {
        System.out.println("Hibernate one to many (Annotation)");
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Contact contact = new Contact();
        contact.setFirstName("Foo");
        contact.setLastName("Bar");
        session.save(contact);

        PhoneNumberEntry phoneNumberEntry = new PhoneNumberEntry();
        phoneNumberEntry.setPhoneNumber("12312313");
        phoneNumberEntry.setPhoneNumberType(PhoneNumberType.FAX);

        phoneNumberEntry.setContact(contact);
        System.out.println(contact.getPhoneNumberEntries());
        contact.getPhoneNumberEntries().add(phoneNumberEntry);

        session.save(phoneNumberEntry);

        session.getTransaction().commit();
        System.out.println("Done");

        Contact contactFromDb = (Contact) session.load(Contact.class, contact.getIdentifier());
        System.out.println(contactFromDb);
        System.out.println(contactFromDb.getPhoneNumberEntries());
        
        System.out.println("Repository calls:");
        
        Contact contact2 = new Contact();
        contact2.setFirstName("Foo");
        contact2.setLastName("Bar");
        
        System.out.println("Save:");
        ContactRepository contactRepository = new ContactRepository();
        contactRepository.save(contact2);
        System.out.println(contact2.getIdentifier());
        
        PhoneNumberEntry phoneNumber = new PhoneNumberEntry();
        phoneNumber.setPhoneNumber("+31123456789");
        phoneNumber.setPhoneNumberType(PhoneNumberType.WORK);
        
        contact.getPhoneNumberEntries().add(phoneNumber);
        phoneNumber.setContact(contact2);
        
        System.out.println("Save:");
        PhoneNumberEntryRepository phoneNumberEntryRepository = new PhoneNumberEntryRepository();
        phoneNumberEntryRepository.save(phoneNumber);
    }

}
