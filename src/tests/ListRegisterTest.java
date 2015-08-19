package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import register.ListRegister;
import register.Person;
import register.Register;

public class ListRegisterTest {

	private Register register;
	private Person person1;
	private Person person2;
	private String exception;

	@Before
	public void setUp() throws Exception {
		register = new ListRegister();
		person1 = new Person("Adam", "80788708908098");
		person2 = new Person("Jozo", "890879878978979889");
		exception = null;
	}

	@Test
	public void addPerson() {

		try {
			register.addPerson(person1);
		} catch (Exception e) {
		}
		assertEquals(register.getPerson(0), person1);
		assertEquals(register.getCount(), 1);
	}

	@Test
	public void addPersonDuplicite() {

		try {
			register.addPerson(person1);
			register.addPerson(person1);
		} catch (Exception e) {
			exception = e.getMessage();
		}
		assertEquals(exception,
				"Person with this name or telephone number already exist in register.\nName and telephone number must be unique. ");
		assertEquals(register.getCount(), 1);
	}

	@Test
	public void findPersonByNameExist() throws Exception {
		register.addPerson(person1);
		assertEquals(register.findPersonByName(person1.getName()), person1);
	}

	@Test
	public void findPersonByNameNotExist() throws Exception {
		try {
			register.findPersonByName(person1.getName());
		} catch (Exception e) {
			exception = e.getMessage();
		}
		assertEquals(exception, "This name does not exist in register.");
	}

	@Test
	public void findPersonByPhoneNumberExist() throws Exception {
		register.addPerson(person1);
		assertEquals(register.findPersonByPhoneNumber(person1.getPhoneNumber()), person1);
	}

	@Test
	public void findPersonByPhoneNumberNotExist() throws Exception {
		try {
			register.findPersonByPhoneNumber(person1.getPhoneNumber());
		} catch (Exception e) {
			exception = e.getMessage();
		}
		assertEquals(exception, "This tel. number does not exist in register.");
	}

	@Test
	public void removePerson() throws Exception {
		try {
			register.removePerson(person1);
		} catch (Exception e) {
			exception = e.getMessage();
		}
		assertEquals(exception, "This name does not exist in register.");
		register.addPerson(person1);
		register.addPerson(person2);
		assertEquals(person1, register.getPerson(0));
		register.removePerson(person1);
		assertEquals(person2, register.getPerson(0));
	}

}
