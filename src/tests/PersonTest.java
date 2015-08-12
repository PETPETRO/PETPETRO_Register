package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import register.*;

public class PersonTest {

	private Person person;
	private Person person2;
	private Person person3;

	@Before
	public void setUp() throws Exception {
		person = new Person("Jano", "545646546564456");
		person2 = new Person("Adam", "1234563456");
		person3 = new Person("Vlado", "09876534223243");
	}

	@Test
	public void setPhoneNumber() throws Exception {
		String newPhoneNumber = "464646848646465465";
		person.setPhoneNumber(newPhoneNumber);
		assertEquals(person.getPhoneNumber(), newPhoneNumber);
	}

	@Test(expected = Exception.class)
	public void InvalidPhoneNumberLength() throws Exception {
		person.setPhoneNumber("123456");
	}

	@Test(expected = Exception.class)
	public void InvalidPhoneNumberContent() throws Exception {
		person.setPhoneNumber("123456dfdertg");
	}

	@Test
	public void ValidPhoneNumberPrefix() throws Exception {
		person.setPhoneNumber("+1234567891");
		assertEquals(person.getPhoneNumber(), "+1234567891");
	}

	@Test
	public void ValidPhoneNumberLength() throws Exception {
		person.setPhoneNumber("232321234567891");
		assertEquals(person.getPhoneNumber(), "232321234567891");
	}

	@Test
	public void compareTo() throws Exception {

		int compare = person2.compareTo(person3);
		assertTrue(compare < 0);
		compare = person3.compareTo(person2);
		assertTrue(compare > 0);
		compare = person2.compareTo(person2);
		assertTrue(compare == 0);

	}
}
