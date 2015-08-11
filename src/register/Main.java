package register;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

/**
 * Created by Peter Petrovaj on 7.8.2015.
 */
public class Main {

	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private static ObjectInputStream pers;

	private static String readLine() {

		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	public static void main(String[] args) throws Exception {

		Register arrayRegister = new ArrayRegister(20);
		Register listRegister = new ListRegister();
		Register register = null;
		ConsoleUI ui = null;

		if (new File("out.bin").exists()) {
			FileInputStream input = new FileInputStream("out.bin");
			pers = new ObjectInputStream(input);
			register = (Register) pers.readObject();
			pers.close();
		}
		System.out.println("Vyberte:\n1.Array\n2.List\n3.Koniec");

		switch (readLine().toString()) {
		case "1":
			arrayRegister = register;
			ui = new ConsoleUI(arrayRegister);
			break;
		case "2":
			listRegister = register;
			ui = new ConsoleUI(listRegister);
			break;

		case "3":
			System.exit(0);
			break;

		default:
			System.out.print("Zadaj 1 alebo 2");

		}
		ui.run();

	}

}
