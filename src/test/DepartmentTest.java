<<<<<<< HEAD
=======

>>>>>>> tests
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

<<<<<<< HEAD
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

=======
>>>>>>> tests
import org.junit.Before;
import org.junit.Test;

import com.vaadin.demo.jpaaddressbook.domain.Department;
import com.vaadin.demo.jpaaddressbook.domain.Person;

public class DepartmentTest {

	Person person = new Person();
	Person person2 = new Person();
	Person person3 = new Person();
	Person person4 = new Person();
	Set<Person> persons = new HashSet();
		
	Department department = new Department();
	Department department2 = new Department();
	Department parentDepartment = new Department();
	
	@Before
	public void setup(){
		//set all persons to a valid state
<<<<<<< HEAD
		String firstName = "first";
		String lastName = "last";
		person.setFirstName(firstName);person.setLastName(lastName);person.setDepartment(department);
		person2.setFirstName(firstName);person2.setLastName(lastName);person2.setDepartment(department);
		person3.setFirstName(firstName);person3.setLastName(lastName);person3.setDepartment(department);
		person4.setFirstName(firstName);person4.setLastName(lastName);person4.setDepartment(department);
=======
		person.setFirstName("first");person.setLastName("last");person.setDepartment(department);person.setPhoneNumber("0560");
		person2.setFirstName("first2");person2.setLastName("last2");person2.setDepartment(department);person.setPhoneNumber("05602");
		person3.setFirstName("first3");person3.setLastName("last3");person3.setDepartment(department);person.setPhoneNumber("05603");
		person4.setFirstName("first4");person4.setLastName("last4");person4.setDepartment(department);person.setPhoneNumber("05604");
>>>>>>> tests
	}
	
	@Test
	public void testPersons() {
		//add persons, the persons added must be the list of persons connected to department
		persons.add(person);
		persons.add(person2);
		persons.add(person3);
		persons.add(person4);
		department.setPersons(persons);
		assertEquals(department.getPersons(),persons);
	}

	@Test
	public void testParent() {
		//multiple departments can have same parent
		department.setParent(parentDepartment);
		department2.setParent(parentDepartment);
		assertEquals(department.getParent(), department2.getParent());
	}


	@Test
	public void testIsSuperDepartment() {
		//if superDepartments is not initialised and size of persons is 0, set superDepartment true. else false
		persons.add(person);
		persons.add(person2);
		persons.add(person3);
		persons.add(person4);
		department.setPersons(persons);
		assertFalse(department.isSuperDepartment());
<<<<<<< HEAD
		//empty list && not initialised is true
=======
		//empty list && not initialized is true
>>>>>>> tests
		persons.clear();
		department2.setPersons(persons);
		assertTrue(department2.isSuperDepartment());
	}

	@Test
	public void testGetHierarchicalName() {
		//same hierachical names
		department.setParent(parentDepartment);
		assertEquals(parentDepartment.toString() + " : " + department.getName(), department.getHierarchicalName());
	}
}
