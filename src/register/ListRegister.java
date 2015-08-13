package register;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListRegister implements Register, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private List<Person> persons;
	// = new ArrayList<Person>();

	public ListRegister() {
		this.persons = new ArrayList<>();
	}

	/**
	 * Returns the number of persons in this register.
	 * 
	 * @return the number of persons in this register
	 */
	@Override
	public int getCount() {
		return persons.size();
	}

	/**
	 * Returns the maximum number of persons in this register.
	 * 
	 * @return the maximum number of persons in this register.
	 */
	@Override
	public int getSize() {
		return persons.size();
	}

	/**
	 * Returns the person at the specified position in this register.
	 * 
	 * @param index
	 *            index of the person to return
	 * @return person the person at the specified position in this register
	 */
	@Override
	public Person getPerson(int index) {
		return persons.get(index);
	}

	public void update() {
		Collections.sort(persons);
	}

	/**
	 * Appends the specified person to the end of this register.
	 * 
	 * @param person
	 *            person to append to this register
	 */
	@Override
	public void addPerson(Person person) throws Exception {
		for (int i = 0; i < this.getSize(); i++) {
			if (person.getName().equals(this.getPerson(i).getName())
					|| person.getPhoneNumber().equals(this.getPerson(i).getPhoneNumber())) {
				throw new Exception("Osoba s takym menom alebo telefonnym cislom uz existuje");
			}
		}

		persons.add(person);
		update();

	}

	/**
	 * Returns the person with specified name in this register or
	 * <code>null</code>, if match can not be found.
	 * 
	 * @param name
	 *            name of a person to search for
	 * @return person with specified phone number
	 */
	@Override
	public Person findPersonByName(String name) throws Exception {

		return persons.stream().filter(s -> s.getName().equals(name)).findFirst()
				.orElseThrow(() -> new Exception("Take meno neexistuje"));

	}

	/**
	 * Returns the person with specified phone number in this register or
	 * <code>null</code>, if match can not be found.
	 * 
	 * @param phoneNumber
	 *            phone number of a person to search for
	 * @return person with specified phone number
	 */
	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) throws Exception {

		return persons.stream().filter(s -> s.getPhoneNumber().equals(phoneNumber)).findFirst()
				.orElseThrow(() -> new Exception("Take tel. cislo neexistuje"));

	}

	/**
	 * Removes the specified person from the register.
	 * 
	 * @param person
	 *            person to remove
	 */
	@Override
	public void removePerson(Person person) throws Exception {

		this.persons.remove(this.findPersonByName(person.getName()));
		update();

	}

}
