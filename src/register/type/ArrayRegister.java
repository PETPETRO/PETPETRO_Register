package register.type;

/**
 * register.Person register.
 */

import java.io.Serializable;

import register.Person;

/**
 * register.Person register.
 */
public class ArrayRegister implements Register, Serializable {

	private static final long serialVersionUID = 1L;

	/** register.Person array. */
	private Person[] persons;

	/** Number of persons in this register. */
	private int count;

	/**
	 * Constructor creates an empty register with maximum size specified.
	 * 
	 * @param size
	 *            maximum size of the register
	 */
	public ArrayRegister(int size) {
		persons = new Person[size];
		count = 0;
	}

	/**
	 * Returns the number of persons in this register.
	 * 
	 * @return the number of persons in this register
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Returns the maximum number of persons in this register.
	 * 
	 * @return the maximum number of persons in this register.
	 */
	public int getSize() {
		return persons.length;
	}

	/**
	 * Returns the person at the specified position in this register.
	 * 
	 * @param index
	 *            index of the person to return
	 * @return person the person at the specified position in this register
	 */
	public Person getPerson(int index) {
		return persons[index];
	}

	/**
	 * Appends the specified person to the end of this register.
	 * 
	 * @param person
	 *            person to append to this register
	 */

	public void addPerson(Person person) throws Exception {

		for (int i = 0; i < count; i++) {
			if (person.getName().equals(persons[i].getName())
					|| person.getPhoneNumber().equals(persons[i].getPhoneNumber())) {
				throw new Exception("Person with this name or telephone number already exist in register.\n"
						+ "Name and telephone number must be unique. ");
			}
		}
		persons[count] = person;
		count++;
	}

	/**
	 * Returns the person with specified name in this register or
	 * <code>null</code>, if match can not be found.
	 * 
	 * @param name
	 *            name of a person to search for
	 * @return person with specified phone number
	 */
	public Person findPersonByName(String name) throws Exception {

		for (int i = 0; i < count; i++) {
			if (name.equals(persons[i].getName())) {
				return persons[i];
			}
		}
		throw new Exception("This name does not exist in register.");
	}

	/**
	 * Returns the person with specified phone number in this register or
	 * <code>null</code>, if match can not be found.
	 * 
	 * @param phoneNumber
	 *            phone number of a person to search for
	 * @return person with specified phone number
	 * @throws Exception
	 */
	public Person findPersonByPhoneNumber(String phoneNumber) throws Exception {
		for (int i = 0; i < count; i++) {
			if (phoneNumber.equals(persons[i].getPhoneNumber())) {
				return persons[i];
			}
		}
		throw new Exception("This tel. number does not exist in register.");
	}

	/**
	 * Removes the specified person from the register.
	 * 
	 * @param person
	 *            person to remove
	 */

	public void removePerson(Person person) throws Exception {

		for (int i = 0; i < count; i++) {

			if (person.equals(getPerson(i))) {
				--count;
				for (; i < count; ++i) {
					persons[i] = persons[i + 1];
				}
				return;
			}
		}
		throw new Exception("This person does not exist in register.");
	}

}
