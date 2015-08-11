package register;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User interface of the application.
 */
public class ConsoleUI {
	/**
	 * 
	 */

	/**
	 * 
	 */

	/** register.Register of persons. */

	private Register register;
	private RegisterLoader databaseLoad = new DatabaseRegisterLoader();

	/**
	 * In JDK 6 use Console class instead.
	 * 
	 * @see readLine()
	 */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Menu options.
	 */
	private enum Option {
		PRINT, ADD, UPDATE, REMOVE, FIND, EXIT
	};

	public ConsoleUI() {
		// register = fileLoad.load();
		register = databaseLoad.load();
		if (register == null) {
			chooseList();
		}

	}

	/**
	 * Options after run
	 * 
	 * @throws Exception
	 */
	public void run() throws Exception {

		while (true) {
			switch (showMenu()) {
			case PRINT:
				printRegister();
				break;
			case ADD:
				addToRegister();
				break;
			case UPDATE:
				updateRegister();
				break;
			case REMOVE:
				removeFromRegister();
				break;
			case FIND:
				findInRegister();
				break;
			case EXIT:

				// this.fileLoad.save(this.register);
				this.databaseLoad.save(this.register);
				return;
			}
		}
	}

	private void chooseList() {
		System.out.println("Vyberte:\n1.Array\n2.List\n3.Koniec");
		String s;

		do {
			s = readLine().toString();
			switch (s) {
			case "1":
				register = new ArrayRegister(20);
				break;
			case "2":
				register = new ListRegister();
				break;

			case "3":
				System.exit(0);
				break;

			default:
				System.out.print("Zadaj 1 alebo 2 alebo 3");

			}
		} while (!s.matches("[123]"));

	}

	/**
	 * Returns string from input
	 * 
	 * @return string from input
	 */
	private String readLine() {

		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * ShowMenu
	 * 
	 * @return
	 */
	private Option showMenu() {
		System.out.println("Menu.");
		for (Option option : Option.values()) {
			System.out.printf("%d. %s%n", option.ordinal() + 1, option);
		}
		System.out.println("-----------------------------------------------");

		int selection = -1;
		do {
			System.out.println("Option: ");
			selection = Integer.parseInt(readLine());
		} while (selection <= 0 || selection > Option.values().length);

		return Option.values()[selection - 1];
	}

	/**
	 * Print all persons from register
	 */
	private void printRegister() {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < register.getCount(); i++) {
			new Formatter(builder).format("%1$-3s %2$-20s %3$-20s %n", (i + 1) + ".", register.getPerson(i).getName(),
					register.getPerson(i).getPhoneNumber());
		}
		System.out.println(builder);
	}

	/**
	 * Add person with telephone number to register
	 */
	private void addToRegister() throws Exception {

		System.out.println("Enter Name: ");
		String name = readLine();

		System.out.println("Enter Phone Number: ");
		String phoneNumber = readLine();
		try {
			register.addPerson(new Person(name, phoneNumber));
		} catch (Exception e) {
			e.getMessage();
			System.err.println(e);
		}

	}

	/**
	 * Update person in register
	 * 
	 * @throws Exception
	 */
	private void updateRegister() throws Exception {
		Pattern p = Pattern.compile("[MT]");
		System.out.println("Enter index: ");
		int index = Integer.parseInt(readLine());
		Person person = register.getPerson(index - 1);
		// register.updatePerson(person);
		System.out.println("Pre zmenu mena zadajte M, pre zmenu tel. cisla zadajte T");
		String mt = readLine().toUpperCase();
		Matcher m = p.matcher(mt);
		if (m.matches()) {
			if (mt.charAt(0) == 'M' && mt.length() == 1) {
				System.out.println("Zadajte meno:");
				String meno = readLine();
				person.setName(meno);
			} else if (mt.charAt(0) == 'T' && mt.length() == 1) {
				System.out.println("Zadajte tel. cislo:");
				String tel = readLine();
				person.setPhoneNumber(tel);
			}
		} else
			System.out.println("Zadal si nespravne");
	}

	/**
	 * Find person in register by name or telephone number
	 * 
	 * @throws Exception
	 */
	private void findInRegister() throws Exception {
		Pattern p = Pattern.compile("[MT]");
		System.out.println("Pre hladanie podla mena zadajte M, pre hladanie tel. cisla zadajte T");
		String mt = readLine().toUpperCase();
		Matcher m = p.matcher(mt);
		Person person;
		if (m.matches()) {
			if (mt.charAt(0) == 'M' && mt.length() == 1) {
				System.out.println("Zadajte meno:");
				String meno = readLine();
				person = register.findPersonByName(meno);
				System.out.println(person.toString());
			} else if (mt.charAt(0) == 'T' && mt.length() == 1) {
				System.out.println("Zadajte tel. cislo:");
				String tel = readLine();
				person = register.findPersonByPhoneNumber(tel);
				System.out.println(person.toString());
			}
		} else
			System.out.println("Zadal si nespravne");
	}

	/**
	 * Remove person from register on index from input
	 * 
	 * @throws Exception
	 */
	private void removeFromRegister() throws Exception {
		System.out.println("Enter index: ");
		int index = Integer.parseInt(readLine());
		try {
			Person person = register.getPerson(index - 1);
			register.removePerson(person);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

}
