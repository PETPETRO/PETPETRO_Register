package register;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class TextFileRegisterLoader implements RegisterLoader {

	private static final String textfile = "TextFileRegisterLoader.txt";

	@Override
	public void save(Register register) {
		File file = new File(textfile);
		try (PrintWriter out = new PrintWriter(file)) {
			for (int i = 0; i < register.getCount(); i++) {
				out.println("" + register.getPerson(i).getName());
				out.println("" + register.getPerson(i).getPhoneNumber());
			}
		} catch (Exception e) {
			e = new Exception("Nieje mozne ulozit subor");
			System.err.println(e);
		}
	}

	@Override
	public Register load() {
		String line;
		Register register = new ListRegister();
		try (FileReader f = new FileReader("TextFileRegisterLoader.txt");
				BufferedReader reader = new BufferedReader(f)) {
			if (new File("TextFileRegisterLoader.txt").exists()) {
				while ((line = reader.readLine()) != null) {

					String meno = line.trim();
					String phone = reader.readLine().trim();
					if (phone == null) {
						throw new Exception("Daco nedobre");
					}
					register.addPerson(new Person(meno, phone));
				}
			}
			return register;
		} catch (Exception e) {
			e = new Exception("Nieje mozne nacitat subor");
			System.err.println(e);
			return null;
		}

	}

}
