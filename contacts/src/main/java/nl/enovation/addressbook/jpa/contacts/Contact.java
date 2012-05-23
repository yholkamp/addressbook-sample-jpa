/*
 * Copyright (c) 2010-2011. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.enovation.addressbook.jpa.contacts;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contact")
public class Contact {

    private Long identifier;

    @Size(min = 1, max = 255)
    private String firstName;

    @Size(min = 2, max = 255)
    private String lastName;

    private String street;

    private String city;

    private String zipCode;

    private Set<PhoneNumberEntry> phoneNumberEntries = new HashSet<PhoneNumberEntry>(0);

    private String department;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<PhoneNumberEntry> getPhoneNumberEntries() {
        return phoneNumberEntries;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Contact other = (Contact) obj;
        if (city == null) {
            if (other.city != null) {
                return false;
            }
        } else if (!city.equals(other.city)) {
            return false;
        }
        if (department == null) {
            if (other.department != null) {
                return false;
            }
        } else if (!department.equals(other.department)) {
            return false;
        }
        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }
        if (identifier == null) {
            if (other.identifier != null) {
                return false;
            }
        } else if (!identifier.equals(other.identifier)) {
            return false;
        }
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        if (phoneNumberEntries == null) {
            if (other.phoneNumberEntries != null) {
                return false;
            }
        } else if (!phoneNumberEntries.equals(other.phoneNumberEntries)) {
            return false;
        }
        if (street == null) {
            if (other.street != null) {
                return false;
            }
        } else if (!street.equals(other.street)) {
            return false;
        }
        if (zipCode == null) {
            if (other.zipCode != null) {
                return false;
            }
        } else if (!zipCode.equals(other.zipCode)) {
            return false;
        }
        return true;
    }

    public String getCity() {
        return city;
    }

    public String getDepartment() {
        return department;
    }

    public String getFirstName() {
        return firstName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long getIdentifier() {
        return identifier;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((department == null) ? 0 : department.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((phoneNumberEntries == null) ? 0 : phoneNumberEntries.hashCode());
        result = prime * result + ((street == null) ? 0 : street.hashCode());
        result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
        return result;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setIdentifier(Long id) {
        identifier = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumberEntries(Set<PhoneNumberEntry> phoneNumbers) {
        this.phoneNumberEntries = phoneNumbers;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    // /**
    // * Adds a phoneNumber to the contact's list and sets the current contact for the PhoneNumberEntry, both without persisting the changes.
    // *
    // * @param phoneNumberEntry
    // */
    // public void addPhoneNumberEntry(PhoneNumberEntry phoneNumberEntry) {
    // if(phoneNumberEntries == null ) {
    // phoneNumberEntries = new ArrayList<PhoneNumberEntry>();
    // }
    // phoneNumberEntries.add(phoneNumberEntry);
    // if (phoneNumberEntry.getContact() != this) {
    // phoneNumberEntry.setContact(this);
    // }
    // }
    //
    // /**
    // * Removes a phoneNumber from the contact's list without persisting the changes.
    // *
    // * @param phoneNumberEntry
    // */
    // public void removePhoneNumberEntry(PhoneNumberEntry phoneNumberEntry) {
    // phoneNumberEntries.remove(phoneNumberEntry);
    // }
}
