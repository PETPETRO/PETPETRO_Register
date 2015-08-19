package register;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileRegisterLoader implements RegisterLoader {
	private static final String REGISTER_FILE = "out.bin";

	public FileRegisterLoader() {

	}

	/**
	 * Save register in binary file.
	 */
	@Override
	public void save(Register register) {
		File file = new File(REGISTER_FILE);
		try (FileOutputStream out = new FileOutputStream(file); ObjectOutputStream s = new ObjectOutputStream(out)) {
			s.writeObject(register);
		} catch (Exception e) {
			e = new Exception("Sorry it is not possible to save file !");
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Load register from binary file to Object of type Register.
	 */
	@Override
	public Register load() {
		File file = new File(REGISTER_FILE);
		Register register;
		if (new File("out.bin").exists()) {

			try (FileInputStream input = new FileInputStream(file);
					ObjectInputStream pers = new ObjectInputStream(input);) {
				register = (Register) pers.readObject();
				return register;
			} catch (Exception e) {
				e = new Exception("Sorry, there is no binary file to load !");
				System.err.println(e.getMessage());
			}
		}
		return null;
	}
}
