package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import register.*;

public class PersonTest {

	private Person person;

	@Before
	public void setUp() throws Exception {
		person = new Person("Jano", "545646546564456");
	}

	@Test
	public void setPhoneNumber() throws Exception {
		String newPhoneNumber = "464646848646465465";
		person.setPhoneNumber(newPhoneNumber);
		assertEquals(person.getPhoneNumber(), newPhoneNumber);
	}

	@Test
	public void InvalidPhoneNumber() {
		Exception exception = null;
		String invalidphoneNumber1 = "123456";
		String invalidphoneNumber2 = "123456dfdertg";

		try {
			person.setPhoneNumber(invalidphoneNumber1);
		} catch (Exception e) {
			exception = e;
		}
		assertEquals(new Exception().getClass(), exception.getClass());

		exception = null;
		try {
			person.setPhoneNumber(invalidphoneNumber2);
		} catch (Exception e) {
			exception = e;
		}
		assertEquals(new Exception().getClass(), exception.getClass());

	}

	@Test
	public void ValidPhoneNumber() throws Exception {
		String validphoneNumber1 = "+1234567891";
		String validphoneNumber2 = "232321234567891";
		person.setPhoneNumber(validphoneNumber1);
		assertEquals(person.getPhoneNumber(), validphoneNumber1);
		person.setPhoneNumber(validphoneNumber2);
		assertEquals(person.getPhoneNumber(), validphoneNumber2);
	}

	@Test
	public void compareTo() throws Exception {
		Person person2 = new Person("Adam", "1234563456");
		Person person3 = new Person("Vlado", "09876534223243");
		int compare = person2.compareTo(person3);
		assertEquals(compare, "Adam".compareToIgnoreCase("Vlado"));
		compare = person3.compareTo(person2);
		assertEquals(compare, "Vlado".compareToIgnoreCase("Adam"));
		compare = person2.compareTo(person2);
		assertEquals(compare, "Adam".compareToIgnoreCase("Adam"));

	}
}
