

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
	// name is shorter than 2 characters
	String nameFalseShort = "f";
	//name is longer than 24 characters
	String nameFalseLong = "falsefalsefalsefalsefalsefalse";
	//name is valid
	String nameTrue = "true";
	//name is null;
	String nameFalseNull;
	
	//valid department
	Department department = new Department();
	//department is null
	Department departmentNull;

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
		//validation is false, because firstName is longer than size 24
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
		
		//set is true
		person.setLastName(nameFalseShort);
		assertEquals(person.getLastName(), nameFalseShort);
		//validation is false because firstName is shorter than size 2
		constraintViolations = validator.validate(person);
		assertEquals(constraintViolations.size(), 1);
		
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
}
