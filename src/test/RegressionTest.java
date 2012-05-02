


import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;

import com.vaadin.demo.jpaaddressbook.domain.*;

public class RegressionTest {

	private String depIn;
	private Person personIn = new Person();
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("addressbook");
	private EntityManager em = emf.createEntityManager();
	private StringBuffer sb = new StringBuffer();
	private String nameLong; 
		
	@Before
	public void setup(){
		//set all values for department and person
		//name for department
		String depIn = "depName";
		//values for valid person
		String firstName = "FirstName";
		String lastName = "LastName";
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
		
		personIn.setDepartment(depIn);
		//generate long string
		for(int x = 0; x < 51; x++){
			sb.append("Fives");
		}
		//make it length 256 length
		sb.append("!");
		nameLong = sb.toString();
	}
		
	@Test
	public void testValidInput() {
		//committing working entities
		//data in:
		em.clear();
		em.getTransaction().begin();
		em.persist(personIn);
		em.getTransaction().commit();

		//data out of the database
		Person personOut = em.find(Person.class, personIn.getId());
				
		//data in == data out
		assertEquals(personIn,personOut);
				
		//clear database
		em.clear();
		em.getTransaction().begin();
		em.remove(em.find(Person.class, personIn.getId()));
		em.getTransaction().commit();	
	}
		
	@Test
	public void testInvalidFirstName(){
		//empty name for invalid cases
		String firstNameEmpty = "";
		
		//Shorter than minimum
		//invalid data for personIn
		personIn.setFirstName(firstNameEmpty);
		boolean gotConstraintViolations = false;
		try{
			em.getTransaction().begin();
			em.persist(personIn);
			em.getTransaction().commit();
		} catch (ConstraintViolationException e){
			assertTrue("Transaction not marked for roll back when ConstraintViolation is thrown", em.getTransaction().getRollbackOnly()) ;
			em.getTransaction().rollback(); // Actually rollback the transaction
			gotConstraintViolations = true;
		}
		assertTrue("Did not get Constraint Violation while persisting with firstName shorter than minimum ", gotConstraintViolations);
		
		//Longer than maximum
		//Invalid data for personIn
		personIn.setFirstName(nameLong);
		
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
		
		//firstname is Null
		//invalid data for personIn
		personIn.setFirstName(null);
		
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
		assertTrue("Did not get Constraint Violation while persisting person with firstname NULL ", gotConstraintViolations);		
	}
	
	@Test
	public void testInvalidLastName(){
		//empty name for invalid cases
		String lastNameShort = "d";
		
		//Shorter than minimum
		//invalid data for personIn
		personIn.setLastName(lastNameShort);
		boolean gotConstraintViolations = false;
		try{
			em.getTransaction().begin();
			em.persist(personIn);
			em.getTransaction().commit();
		} catch (ConstraintViolationException e){
			assertTrue("Transaction not marked for roll back when ConstraintViolation is thrown", em.getTransaction().getRollbackOnly()) ;
			em.getTransaction().rollback(); // Actually rollback the transaction
			gotConstraintViolations = true;
		}
		assertTrue("Did not get Constraint Violation while persisting with lastName shorter than minimum ", gotConstraintViolations);
		
		//Longer than maximum
		//Invalid data for personIn
		personIn.setLastName(nameLong);
		
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
		assertTrue("Did not get Constraint Violation while persisting with lastName longer than maximum", gotConstraintViolations);
		
		//lastname null
		//Invalid data for personIn
		personIn.setLastName(null);
		
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
		assertTrue("Did not get Constraint Violation while persisting with lastName null", gotConstraintViolations);
	}
	
	@Test
	public void testInvalidPhoneDep(){
		//PhoneNumber is Null
		//invalid data for personIn
		personIn.setPhoneNumber(null);
		
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
		assertTrue("Did not get Constraint Violation while persisting person with phoneNumber NULL ", gotConstraintViolations);
		
		//Department is Null
		//invalid data for personIn
		personIn.setDepartment(null);
		
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
	
	@Test
	public void testDoubleAdd(){
		
		Person personDouble = new Person();
		//set all values for department and person
		//name for department
		String depName = "depName";
		
		//values for valid person
		String firstName = "FirstName";
		String lastName = "LastName";
		String city = "City";
		String phoneNumber = "06066060";
		
		//set all the values		
		personDouble.setCity(city);
		personDouble.setDepartment(depIn);
		personDouble.setFirstName(firstName);
		personDouble.setLastName(lastName);
		personDouble.setPhoneNumber(phoneNumber);
				
		depIn = depName;
		personDouble.setDepartment(depIn);
		
		boolean gotDoubleViolation = false;
		try{
			em.getTransaction().begin();
			em.persist(personIn);
			em.persist(personDouble);
			em.getTransaction().commit();
		}
		catch (RollbackException e){
			//em.getTransaction().rollback(); // Actually rollback is already done. none of persons is persisted
			gotDoubleViolation = true;
		}
		assertTrue("Did not get Constraint Violation while persisting person with not unique IDtupple (firstname, lastname, phonenumber) ", gotDoubleViolation);
		
	}
}
