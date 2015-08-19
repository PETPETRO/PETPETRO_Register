package register;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User interface of the application.
 */
public class ConsoleUI {

	/** register.Register of persons. */

	private Register register = null;
	private RegisterLoader fileLoad = new FileRegisterLoader();
	private RegisterLoader databaseLoad = new DatabaseRegisterLoader();
	private RegisterLoader textLoad = new TextFileRegisterLoader();
	private RegisterLoader loader = null;
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

		Register file = fileLoad.load();
		Register text = textLoad.load();
		Register database = databaseLoad.load();
		int i = 1;

		System.out.println("Which register do you want to load ?\n");
		if (file != null) {
			System.out.printf("%-30s %7s%n", i++ + ". Register from binary file.", "Enter B");
		}
		if (text != null) {
			System.out.printf("%-30s %7s%n", i++ + ". Register from text file.", "Enter T");
		}
		if (database != null) {
			System.out.printf("%-30s %7s%n", i++ + ". Register from database.", "Enter D");
		}
		System.out.printf("%-30s %7s%n", i++ + ". New register", "Enter N");

		do {
			String input = readLine().toString().toUpperCase();
			if (input.equals("B") && file != null) {
				register = file;
				return;
			}
			if (input.equals("T") && text != null) {
				register = text;
				return;
			}
			if (input.equals("D") && database != null) {
				register = database;
				return;
			}
			if (input.equals("N")) {
				chooseList();
				return;
			}
			System.err.println("Incorect input!");
		} while (!input.equals("B") || !input.equals("T") || !input.equals("D") || !input.equals("N"));
	}

	/**
	 * Options after run
	 * 
	 * @throws Exception
	 */
	public void run() throws Exception {
		while (true) {
			try {
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
					save();
					return;
				}
			} catch (Exception e) {
				e = new Exception("Incorect input! Enter 1-6.");
				System.err.println(e.getMessage());
			}
		}
	}

	/**
	 * Save register in text file, binary file or database
	 */
	private void save() {
		System.out.println("Where do you want to save your register ?\n");
		System.out.printf("%-30s %7s %n", "1. Binary file", "Enter 1");
		System.out.printf("%-30s %7s %n", "2. Text file", "Enter 2");
		System.out.printf("%-30s %7s %n", "3. Database", "Enter 3");
		System.out.printf("%-30s %7s %n", "4. Exit without saving", "Enter 4");
		do {
			String input = readLine().toString();
			if (input.equals("1")) {
				loader = fileLoad;
				break;
			} else if (input.equals("2")) {
				loader = textLoad;
				break;
			} else if (input.equals("3")) {
				loader = databaseLoad;
				break;
			} else if (input.equals("4")) {
				System.exit(0);
			} else
				System.err.println("Incorect Input enter 1-4.");
		} while (!input.equals("1") || !input.equals("2") || !input.equals("3") || !input.equals("4"));

		this.loader.save(this.register);
	}

	/**
	 * Create the chosen register
	 */
	private void chooseList() {
		System.out.println("Choose:\n1.Array\n2.List\n3.End");
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

		System.out.println("-------------");
		System.out.printf("%s" + "%7s" + "%5s%n", "|", "Menu", "|");
		System.out.println("-------------");
		for (Option option : Option.values()) {
			System.out.printf("%s" + "%d. %-6s" + "%3s%n", "|", option.ordinal() + 1, option, "|");
		}
		System.out.println("-------------");

		int selection = -1;
		System.out.println("Option: ");
		selection = Integer.parseInt(readLine());

		return Option.values()[selection - 1];
	}

	/**
	 * Print all persons from register
	 */
	private void printRegister() {
		StringBuilder builder = new StringBuilder();
		System.out.println("------------------------------------------------");
		System.out.printf("%1$-3s %2$-20s %3$-20s  %4$1s%n", "|", "Name", "Telephone Number", "|");
		System.out.println("------------------------------------------------");
		for (int i = 0; i < register.getCount(); i++) {
			new Formatter(builder).format("%1$-3s %2$-20s %3$-20s  %4$1s%n", "|" + (i + 1) + ".",
					register.getPerson(i).getName(), register.getPerson(i).getPhoneNumber(), "|");
		}
		System.out.print(builder);
		System.out.println("------------------------------------------------");
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
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Update person in register
	 * 
	 * @throws Exception
	 */
	private void updateRegister() {

		try {
			Pattern p = Pattern.compile("M|T|MT");
			System.out.println("Enter index: ");
			int index = Integer.parseInt(readLine());
			Person person = register.getPerson(index - 1);
			System.out.println("If you only want to change name enter \"M\",\n"
					+ "if you only want to change  tel. number enter \"T\",\n"
					+ "if you want to change both name and tel. number enter \"MT\".");
			String mt = readLine().toUpperCase();
			Matcher m = p.matcher(mt);
			if (m.matches()) {
				if (mt.equals("M")) {
					System.out.println("Enter name:");
					String meno = readLine();
					person.setName(meno);
				} else if (mt.equals("T")) {
					System.out.println("Enter tel. number:");
					String tel = readLine();
					person.setPhoneNumber(tel);
				} else if (mt.equals("MT")) {
					System.out.println("Enter name:");
					String meno = readLine();
					person.setName(meno);
					System.out.println("Enter tel. number:");
					String tel = readLine();
					person.setPhoneNumber(tel);
				}
			} else
				System.out.println("Incorect input! Enter \"M\" or \"T\"");
		} catch (Exception e) {
			e = new Exception("Index is out of range !\nEnter value from 1-" + register.getCount());
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Find person in register by name or telephone number
	 * 
	 * @throws Exception
	 */
	private void findInRegister() throws Exception {
		try {
			Pattern p = Pattern.compile("[MT]");
			System.out.println(
					"If you want to search by name enter \"M\", if you want to search by tel. number enter \"T\".");
			String mt = readLine().toUpperCase();
			Matcher m = p.matcher(mt);
			Person person;
			if (m.matches()) {
				if (mt.equals("M")) {
					System.out.println("Enter name:");
					String meno = readLine();
					person = register.findPersonByName(meno);
					System.out.println(person.toString());
				} else if (mt.equals("T")) {
					System.out.println("Enter tel. number:");
					String tel = readLine();
					person = register.findPersonByPhoneNumber(tel);
					System.out.println(person.toString());
				}
			} else
				System.out.println("Incorect input! Enter \"M\" or \"T\"");

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

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
			e = new Exception("Index is out of range !\nEnter value from 1-" + register.getCount());
			System.err.println(e.getMessage());
		}

	}

}
