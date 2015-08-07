package register;

/**
 * Created by Peter Petrovaj on 7.8.2015.
 */
public class Main {

	public static void main(String[] args) throws Exception {
		ArrayRegister arrayRegister = new ArrayRegister(20);
		ListRegister register = new ListRegister();

		arrayRegister.addPerson(new Person("Peter Petrovaj", "0935123456"));
		arrayRegister.addPerson(new Person("Janko Hrasko", "+23565662655"));
		arrayRegister.addPerson(new Person("Adam Hrasko", "46546545646456"));

		register.addPerson(new Person("Peter Petrovaj", "0935123456"));
		register.addPerson(new Person("Janko Hrasko", "+23565662655"));
		register.addPerson(new Person("Adam Hrasko", "46546545646456"));

		ConsoleUI uar = new ConsoleUI(arrayRegister);
		ConsoleUI ulr = new ConsoleUI(register);

		// System.out.println("Vyberte si rozhranie 1\\2
		// :\n1.ArrayRegister\n2.ListRegister");
		// String input = System.console().readLine();
		// if (input == "1") {
		// uar.run();
		// } else if (input == "2") {
		// ulr.run();
		// } else
		// System.err.println("Zadal si nespravne");
		//
		// }

		ulr.run();
	}
}
