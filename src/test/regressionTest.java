

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolationException;

import org.junit.Test;

import com.vaadin.demo.jpaaddressbook.domain.*;

public class regressionTest {

	private Department depIn = new Department();
	private Set<Person> persons = new HashSet();
	private Person personIn = new Person();
		
	@Test
	public void testValidInput() {
		
		//set all values for department and person
		//name for department
		String depName = "depName";
		//values for valid person
		String firstName = "FirstName";
		String lastName = "Lastname";
		String city = "City";
		String phoneNumber = "06066060";
		String street = "street";
		String zipCode = "3542ss";
		//set all the values		
		personIn.setCity(city);
		personIn.setDepartment(depIn);
		personIn.setFirstName(firstName);
		personIn.setLastName(lastName);
		personIn.setPhoneNumber(phoneNumber);
		personIn.setStreet(street);
		personIn.setZipCode(zipCode);
		
		depIn.setName(depName);
		persons.add(personIn);		
		depIn.setPersons(persons);
		personIn.setDepartment(depIn);
		
		EntityManager em = Persistence.createEntityManagerFactory("addressbook").createEntityManager();
		
		//committing working entities
		//data in:
		em.persist(personIn);
		em.persist(depIn);
		
		//data out
		Person personOut = em.find(Person.class, personIn.getId());
		Department depOut = em.find(Department.class, depIn.getId());
		//data in == data out
		assertEquals(personIn,personOut);
		assertEquals(depOut, depIn);
		em.clear();
	}
		
	@Test
	public void testInvalidInput(){
		//set all values for department and person
		//name for department
		String depName = "depName";
		//values for valid person
		String firstName = "FirstName";
		String firstNameLong = "falsefalsefalsefalsefalsefalse";
		String firstNameShort = "f";
		String lastName = "Lastname";
		String city = "City";
		String phoneNumber = "06066060";
		String street = "street";
		String zipCode = "3542ss";
		//set all the values		
		personIn.setCity(city);
		personIn.setDepartment(depIn);
		personIn.setFirstName(firstName);
		personIn.setLastName(lastName);
		personIn.setPhoneNumber(phoneNumber);
		personIn.setStreet(street);
		personIn.setZipCode(zipCode);
		
		depIn.setName(depName);
		persons.add(personIn);		
		depIn.setPersons(persons);
		personIn.setDepartment(depIn);
		
		//Shorter than minimum
		EntityManager em = Persistence.createEntityManagerFactory("addressbook").createEntityManager();
		em.clear();
		//invalid data for personIn
		personIn.setFirstName(firstNameShort);
		
		boolean gotConstraintViolations = false;
		try{
			em.getTransaction().begin();
			em.persist(personIn);
			em.getTransaction().commit();
		}
		catch (ConstraintViolationException e){
			assertTrue("Transaction not marked for roll back when ConstraintViolation is thrown", em.getTransaction().getRollbackOnly()) ;
			em.getTransaction().rollback(); // Actually rollback the transaction
			gotConstraintViolations = true;
		}
		assertTrue("Did not get Constraint Violation while persisting with firstName shorter than minimum ", gotConstraintViolations);
		
		
		//Longer than maximum
		em.clear();
		//Invalid data for personIn
		personIn.setFirstName(firstNameLong);
		
		gotConstraintViolations = false;
		try{
			em.getTransaction().begin();
			em.persist(personIn);
			em.getTransaction().commit();
		}
		catch (ConstraintViolationException e){
			assertTrue("Transaction not marked for roll back when ConstraintViolation is thrown", em.getTransaction().getRollbackOnly()) ;
			em.getTransaction().rollback(); // Actually rollback the transaction
			gotConstraintViolations = true;
		}
		assertTrue("Did not get Constraint Violation while persisting with firstName longer than maximum", gotConstraintViolations);
		
		//Department is Null
		em.clear();
		//invalid data for personIn
		Department depNull = new Department();
		personIn.setDepartment(depNull);
		
		gotConstraintViolations = false;
		try{
			em.getTransaction().begin();
			em.persist(personIn);
			em.getTransaction().commit();
		}
		catch (ConstraintViolationException e){
			assertTrue("Transaction not marked for roll back when ConstraintViolation is thrown", em.getTransaction().getRollbackOnly()) ;
			em.getTransaction().rollback(); // Actually rollback the transaction
			gotConstraintViolations = true;
		}
		assertTrue("Did not get Constraint Violation while persisting person with department NULL ", gotConstraintViolations);
		
	}
}
