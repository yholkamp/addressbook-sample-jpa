package nl.enovation.addressbook.jpa.pojo;

import static org.hibernate.annotations.CascadeType.DETACH;
import static org.hibernate.annotations.CascadeType.LOCK;
import static org.hibernate.annotations.CascadeType.MERGE;
import static org.hibernate.annotations.CascadeType.PERSIST;
import static org.hibernate.annotations.CascadeType.REFRESH;
import static org.hibernate.annotations.CascadeType.REPLICATE;
import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;

/**
 * Basic POJO representing the phoneNumberEntries in our sample application. Contains phone number and phone number type as well as an identifier used by Hibernate.
 * 
 * @author Yorick Holkamp
 */
@Entity
@Table(name = "phonenumberentry")
public class PhoneNumberEntry {

    private Long identifier;

    private String phoneNumber;

    @NotNull
    private PhoneNumberType phoneNumberType;

    private Contact contact;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @Cascade({ PERSIST, MERGE, REFRESH, SAVE_UPDATE, REPLICATE, LOCK, DETACH })
    @JoinColumn(name = "CONTACT_ID", nullable = false)
    public Contact getContact() {
        return contact;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdentifier() {
        return identifier;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PhoneNumberType getPhoneNumberType() {
        return phoneNumberType;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPhoneNumberType(PhoneNumberType phoneNumberType) {
        this.phoneNumberType = phoneNumberType;
    }
}
