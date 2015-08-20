package register.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import register.Person;
import register.type.ArrayRegister;
import register.type.Register;

public class ArrayRegisterTest {

	private Register register;
	private Person person1;
	private Person person2;
	private String exception;

	@Before
	public void setUp() throws Exception {
		register = new ArrayRegister(30);
		person1 = new Person("Adam", "80788708908098");
		person2 = new Person("Jozo", "890879878978979889");
		exception = null;
	}

	@Test
	public void addPersonSuccess() {

		try {
			register.addPerson(person1);
		} catch (Exception e) {
		}
		assertEquals(register.getPerson(0), person1);
		assertEquals(register.getCount(), 1);

	}

	@Test
	public void addPersonFail() {
		try {
			register.addPerson(person1);
			register.addPerson(person1);
		} catch (Exception e) {
			exception = e.getMessage();
		}
		assertEquals(exception, "Person with this name or telephone number already exist in register.\n"
				+ "Name and telephone number must be unique. ");
		assertEquals(register.getCount(), 1);

	}

	@Test
	public void findPersonByNameFail() throws Exception {
		try {
			register.findPersonByName(person1.getName());
		} catch (Exception e) {
			exception = e.getMessage();
		}
		assertEquals(exception, "This name does not exist in register.");
	}

	@Test
	public void findPersonByNameSucces() throws Exception {
		register.addPerson(person1);
		assertEquals(register.findPersonByName(person1.getName()), person1);
	}

	@Test
	public void findPersonByPhoneNumberFail() throws Exception {
		try {
			register.findPersonByPhoneNumber(person1.getPhoneNumber());
		} catch (Exception e) {
			exception = e.getMessage();
		}
		assertEquals(exception, "This tel. number does not exist in register.");

	}

	@Test
	public void findPersonByPhoneNumberSucces() throws Exception {
		register.addPerson(person1);
		assertEquals(register.findPersonByPhoneNumber(person1.getPhoneNumber()), person1);
	}

	@Test
	public void removePersonFail() throws Exception {
		try {
			register.removePerson(person1);
		} catch (Exception e) {
			exception = e.getMessage();
		}
		assertEquals(exception, "This person does not exist in register.");

	}

	@Test
	public void removePersonSucces() throws Exception {
		register.addPerson(person1);
		register.addPerson(person2);
		assertEquals(person1, register.getPerson(0));
		register.removePerson(person1);
		assertEquals(person2, register.getPerson(0));
	}
}
