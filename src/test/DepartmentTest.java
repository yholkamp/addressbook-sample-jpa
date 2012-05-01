import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;


import org.junit.Before;
import org.junit.Test;

import com.vaadin.demo.jpaaddressbook.domain.Department;
import com.vaadin.demo.jpaaddressbook.domain.Person;

public class DepartmentTest {

	Person person = new Person();
	Person person2 = new Person();
	Person person3 = new Person();
	Person person4 = new Person();
	Set<Person> persons = new HashSet<Person>();
		
	Department department = new Department();
	Department department2 = new Department();
	Department parentDepartment = new Department();
	
	@Before
	public void setup(){
		//set all persons to a valid state
		person.setFirstName("first");person.setLastName("last");person.setDepartment(department);person.setPhoneNumber("0560");
		person2.setFirstName("first2");person2.setLastName("last2");person2.setDepartment(department);person.setPhoneNumber("05602");
		person3.setFirstName("first3");person3.setLastName("last3");person3.setDepartment(department);person.setPhoneNumber("05603");
		person4.setFirstName("first4");person4.setLastName("last4");person4.setDepartment(department);person.setPhoneNumber("05604");
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
}
