package register;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * register.Person.
 */
public class Person implements Comparable<Person>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Name of this person. */
	private String name;

	/** Phone number of this person. */
	private String phoneNumber;

	/**
	 * Construct a person.
	 * 
	 * @param name
	 *            name of the person
	 * @param phoneNumber
	 *            phone number of the person
	 * @throws Exception
	 */
	public Person(String name, String phoneNumber) throws Exception {

		this.name = name;
		this.setPhoneNumber(phoneNumber);

	}

	/**
	 * Returns name of this person.
	 * 
	 * @return name of this person
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name of this person.
	 * 
	 * @param nameNew
	 *            name of this person
	 */
	public void setName(String nameNew) {
		name = nameNew;
	}

	/**
	 * Returns phone number of this person.
	 * 
	 * @return phone number of this person
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets phone number of this person.
	 * 
	 * @param phoneNumberNew
	 *            phone number of this person
	 */
	public void setPhoneNumber(String phoneNumberNew) throws Exception {
		if (isValidPhoneNumber(phoneNumberNew)) {
			phoneNumber = phoneNumberNew;
			return;
		}
		throw new Exception("Phone number is not valid");
	}

	/**
	 * Validates the phone number. Valid phone numbers contains only digits.
	 * 
	 * @param phoneNumber
	 *            phone number to validate
	 * @return <code>true</code> if phone number is valid, <code>false</code>
	 *         otherwise
	 */
	private boolean isValidPhoneNumber(String phoneNumber) {
		Pattern p = Pattern.compile("[+]?[0-9]{10,}");
		Matcher m = p.matcher(phoneNumber);
		return m.matches();
	}

	/**
	 * Returns a string representation of the person.
	 * 
	 * @return string representation of the person.
	 */
	public String toString() {
		return name + " (" + phoneNumber + ")";
	}

	/**
	 * Returns integer which represents which object is greater
	 * 
	 * @return 0 if compared objects are equals, 1 if this object is greater,-1
	 *         if object in parameter is greater
	 */
	@Override
	public int compareTo(Person o) throws NullPointerException {
		return this.getName().compareToIgnoreCase(o.getName());

	}

}
