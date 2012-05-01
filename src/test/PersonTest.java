

<<<<<<< HEAD
=======

>>>>>>> tests
import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import com.vaadin.demo.jpaaddressbook.domain.Department;
import com.vaadin.demo.jpaaddressbook.domain.Person;

public class PersonTest {		
	Person person = new Person();
<<<<<<< HEAD
	// name is shorter than 2 characters
	String nameFalseShort = "f";
	//name is longer than 24 characters
	String nameFalseLong = "falsefalsefalsefalsefalsefalse";
=======
	StringBuffer sb = new StringBuffer();
	// name is shorter than 1 character
	String nameFalseShort = "";
	String nameFalseShorter = "f";
	//name is longer than 24 characters
	String nameFalseLong;
>>>>>>> tests
	//name is valid
	String nameTrue = "true";
	//name is null;
	String nameFalseNull;
	
	//valid department
	Department department = new Department();
	//department is null
	Department departmentNull;
<<<<<<< HEAD
=======
	
	//valid phonenumber
	String phoneNumber = "064834";
	//null number
	String phoneNumberNull;
>>>>>>> tests

	//validator init
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();
	
	@Before
	public void constraintsValid(){ 
		//all attributes that have constraints are set to a legal state
		person.setFirstName(nameTrue);
		assertEquals(person.getFirstName(), nameTrue);
		person.setLastName(nameTrue);
		assertEquals(person.getLastName(), nameTrue);
		person.setDepartment(department);
		assertEquals(person.getDepartment(), department);
<<<<<<< HEAD
=======
		person.setPhoneNumber(phoneNumber);
		assertEquals(person.getPhoneNumber(), phoneNumber);
		//generate long string
		for(int x = 0; x < 51; x++){
			sb.append("Fives");
		}
		//make it length 256 length
		sb.append("!");
		nameFalseLong = sb.toString();
>>>>>>> tests
	}

	@Test
	public void testFirstName() {		
		//validation is true, because all constraints are legal
		Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
		assertEquals(constraintViolations.size(), 0);
		
		//set is true
		person.setFirstName(nameFalseShort);
		assertEquals(person.getFirstName(), nameFalseShort);
		//validation is false because firstName is shorter than size 2
		constraintViolations = validator.validate(person);
		assertEquals(constraintViolations.size(), 1);
		
		//set is true
		person.setFirstName(nameFalseLong);
		assertEquals(person.getFirstName(), nameFalseLong);
<<<<<<< HEAD
		//validation is false, because firstName is longer than size 24
=======
		//validation is false, because firstName is longer than size 255
>>>>>>> tests
		constraintViolations = validator.validate(person);
		assertEquals(constraintViolations.size(), 1);
		
		//set is true
		person.setFirstName(nameFalseNull);
		assertEquals(person.getFirstName(), null);
		//validation is false, because firstName is null
		constraintViolations = validator.validate(person);
		assertEquals(constraintViolations.size(), 1);				
	}

	@Test
	public void testLastName() {
		//validation is true, because all constraints are legal
		Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
		assertEquals(constraintViolations.size(), 0);
		
<<<<<<< HEAD
		//set is true
		person.setLastName(nameFalseShort);
		assertEquals(person.getLastName(), nameFalseShort);
=======
		//1 violation, short name
		person.setLastName(nameFalseShorter);
		assertEquals(person.getLastName(), nameFalseShorter);
>>>>>>> tests
		//validation is false because firstName is shorter than size 2
		constraintViolations = validator.validate(person);
		assertEquals(constraintViolations.size(), 1);
		
<<<<<<< HEAD
		//set is true
		person.setLastName(nameFalseLong);
		assertEquals(person.getLastName(), nameFalseLong);
		//validation is false, because lastName is longer than size 24
		constraintViolations = validator.validate(person);
		assertEquals(constraintViolations.size(), 1);
		
		//set is true
		person.setLastName(nameFalseNull);
		assertEquals(person.getLastName(), null);
		//validation is true, because lastName can be null
		constraintViolations = validator.validate(person);
		assertEquals(constraintViolations.size(), 0);
=======
		//1 violation long name
		person.setLastName(nameFalseLong);
		assertEquals(person.getLastName(), nameFalseLong);
		//validation is false, because lastName is longer than size 255
		constraintViolations = validator.validate(person);
		assertEquals(constraintViolations.size(), 1);
		
		//1 violation null
		person.setLastName(nameFalseNull);
		assertEquals(person.getLastName(), null);
		//validation is true, because lastName cant be null
		constraintViolations = validator.validate(person);
		assertEquals(constraintViolations.size(), 1);
>>>>>>> tests
	}

	@Test
	public void testDepartment() {
		//validation is true, because all constraints are legal
		Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
		assertEquals(constraintViolations.size(), 0);
				
		//valid with one department
		person.setDepartment(department);
		assertEquals(person.getDepartment(), department);
		//validation is true, because there is a department, only one is possible
		constraintViolations = validator.validate(person);
		assertEquals(constraintViolations.size(), 0);
		
		//valid with null
		person.setDepartment(departmentNull);
		assertEquals(person.getDepartment(), departmentNull);
		//validation is false, because null is not valid
		constraintViolations = validator.validate(person);
		assertEquals(constraintViolations.size(), 1);		
	}
<<<<<<< HEAD
=======
	
	@Test
	public void testPhoneNumber(){
		//validation is true, because all constraints are legal
		Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
		assertEquals(constraintViolations.size(), 0);	
		
		//phonenumber may not be null
		person.setPhoneNumber(phoneNumberNull);
		//validation is false, because null is not valid
		constraintViolations = validator.validate(person);
		assertEquals(constraintViolations.size(), 1);	
	}
>>>>>>> tests
}
