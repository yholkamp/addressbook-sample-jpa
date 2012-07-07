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
package nl.enovation.addressbook.jpa.pojo;

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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * A basic POJO representing a contact in our sample application.
 * 
 * @author Maarten van Meijeren
 */
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

    private Set<PhoneNumberEntry> phoneNumbers = new HashSet<PhoneNumberEntry>(0);

    private String department;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Contact) {
            final Contact other = (Contact) obj;
            return new EqualsBuilder().append(identifier, other.getIdentifier()).append(firstName, other.getFirstName()).append(lastName, other.getLastName())
                                      .append(street, other.getStreet()).append(city, other.getCity()).append(zipCode, other.getZipCode())
                                      .append(phoneNumbers, other.getPhoneNumbers()).append(department, other.getDepartment()).isEquals();
        } else {
            return false;
        }
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<PhoneNumberEntry> getPhoneNumbers() {
        return phoneNumbers;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(identifier).append(firstName).append(lastName).append(street).append(city).append(zipCode)
                                    .append(phoneNumbers.hashCode()).append(department).toHashCode();
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

    public void setPhoneNumbers(Set<PhoneNumberEntry> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
