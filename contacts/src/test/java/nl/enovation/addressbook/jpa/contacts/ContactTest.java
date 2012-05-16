package nl.enovation.addressbook.jpa.contacts;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContactTest {
    Contact contact = new Contact();

    StringBuffer sb = new StringBuffer();

    // name is shorter than 1 character
    String nameFalseShort = "";

    String nameFalseShorter = "f";

    // name is longer than 255 characters
    String nameFalseLong;

    // name is valid
    String nameTrue = "true";

    // name is null;
    String nameFalseNull;

    // valid department
    String department = "department";

    // department is null
    String departmentNull;

    // invalid phoneNumbers
    String phoneNumberShort = "06483";

    String phoneNumberLong = "123451234512345";

    // valid phoneNumber
    String phoneNumber = "061234";

    // null number
    String phoneNumberNull;

    // validator
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private Validator validator = factory.getValidator();

    @Before
    public void constraintsValid() {
        // all attributes that have constraints are set to a legal state
        contact.setFirstName(nameTrue);
        assertEquals(contact.getFirstName(), nameTrue);
        contact.setLastName(nameTrue);
        assertEquals(contact.getLastName(), nameTrue);
        contact.setDepartment(department);
        assertEquals(contact.getDepartment(), department);

        // generate long string
        for (int x = 0; x < 51; x++) {
            sb.append("Fives");
        }
        // make it length 256 length
        sb.append("!");
        nameFalseLong = sb.toString();

    }

    @Test
    public void testDepartment() {
        // validation is true, because all constraints are legal
        Set<ConstraintViolation<Contact>> constraintViolations = validator.validate(contact);
        assertEquals(constraintViolations.size(), 0);

        // valid with one department
        contact.setDepartment(department);
        assertEquals(contact.getDepartment(), department);
        // validation is true, because there is a department, only one is possible
        constraintViolations = validator.validate(contact);
        assertEquals(constraintViolations.size(), 0);

        // valid with null
        contact.setDepartment(departmentNull);
        assertEquals(contact.getDepartment(), departmentNull);
        // validation is true, because null is valid
        constraintViolations = validator.validate(contact);
        assertEquals(constraintViolations.size(), 0);
    }

    @Test
    public void testFirstName() {
        // validation is true, because all constraints are legal
        Set<ConstraintViolation<Contact>> constraintViolations = validator.validate(contact);
        assertEquals(constraintViolations.size(), 0);

        // set is true
        contact.setFirstName(nameFalseShort);
        assertEquals(contact.getFirstName(), nameFalseShort);
        // validation is false because firstName is shorter than size 2
        constraintViolations = validator.validate(contact);
        assertEquals(constraintViolations.size(), 1);

        // set is true
        contact.setFirstName(nameFalseLong);
        assertEquals(contact.getFirstName(), nameFalseLong);
        // validation is false, because firstName is longer than size 255
        constraintViolations = validator.validate(contact);
        assertEquals(constraintViolations.size(), 1);

        // set is true
        contact.setFirstName(nameFalseNull);
        assertEquals(contact.getFirstName(), null);
        // validation is false, because firstName is null
        constraintViolations = validator.validate(contact);
        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    public void testLastName() {
        // validation is true, because all constraints are legal
        Set<ConstraintViolation<Contact>> constraintViolations = validator.validate(contact);
        assertEquals(constraintViolations.size(), 0);

        // 1 violation, short name
        contact.setLastName(nameFalseShorter);
        assertEquals(contact.getLastName(), nameFalseShorter);

        // validation is false because firstName is shorter than size 2
        constraintViolations = validator.validate(contact);
        assertEquals(constraintViolations.size(), 1);

        // 1 violation long name
        contact.setLastName(nameFalseLong);
        assertEquals(contact.getLastName(), nameFalseLong);
        // validation is false, because lastName is longer than size 255
        constraintViolations = validator.validate(contact);
        assertEquals(constraintViolations.size(), 1);

        // 1 violation null
        contact.setLastName(nameFalseNull);
        assertEquals(contact.getLastName(), null);
        // validation is true, because lastName cant be null
        constraintViolations = validator.validate(contact);
        assertEquals(constraintViolations.size(), 1);
    }

}
