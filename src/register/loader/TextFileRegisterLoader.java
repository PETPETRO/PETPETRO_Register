package register.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import register.Person;
import register.type.ListRegister;
import register.type.Register;

public class TextFileRegisterLoader implements RegisterLoader {

	private static final String textfile = "TextFileRegisterLoader.txt";

	/**
	 * Save register from parameter into text file.
	 * 
	 * @param register
	 */
	@Override
	public void save(Register register) {
		File file = new File(textfile);
		try (PrintWriter out = new PrintWriter(file)) {
			for (int i = 0; i < register.getCount(); i++) {
				out.println("" + register.getPerson(i).getName());
				out.println("" + register.getPerson(i).getPhoneNumber());
			}
		} catch (Exception e) {
			e = new Exception("Sorry it is not possible to save file !");
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Load register from text file into variable of type Register.
	 * 
	 */
	@Override
	public Register load() {
		String line;
		Register register = new ListRegister();
		if (new File("TextFileRegisterLoader.txt").exists()) {

			try (FileReader f = new FileReader("TextFileRegisterLoader.txt");
					BufferedReader reader = new BufferedReader(f)) {
				while ((line = reader.readLine()) != null) {
					String meno = line.trim();
					String phone = reader.readLine().trim();
					if (phone == null) {
						throw new Exception("Daco nedobre");
					}
					register.addPerson(new Person(meno, phone));
				}
				return register;
			} catch (Exception e) {
				e = new Exception("Sorry, there is no text file to load !");
				System.err.println(e.getMessage());
			}
		}
		return null;
	}
}
