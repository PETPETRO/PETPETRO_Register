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

	/* (non-Javadoc)
	 * @see register.RegisterLoader#save(register.Register)
	 */
	@Override
	public void save(Register register) {
		File file = new File(REGISTER_FILE);
		try (FileOutputStream out = new FileOutputStream(file); ObjectOutputStream s = new ObjectOutputStream(out)) {
			s.writeObject(register);
		} catch (Exception e) {
			e = new Exception("Nieje mozne ulozit subor");
			System.err.println(e);
		}
	}

	/* (non-Javadoc)
	 * @see register.RegisterLoader#load()
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
				e = new Exception("Nieje mozne nacitat subor");
				System.err.println(e);
			}

		}
		return null;
	}
}
