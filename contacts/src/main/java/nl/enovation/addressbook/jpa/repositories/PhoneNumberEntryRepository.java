package nl.enovation.addressbook.jpa.repositories;

import java.util.List;

import nl.enovation.addressbook.jpa.contacts.PhoneNumberEntry;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

public interface PhoneNumberEntryRepository extends JpaRepository<PhoneNumberEntry, Long>  {

    public List<PhoneNumberEntry> findByPhoneNumber(String phoneNumber);
    
}
