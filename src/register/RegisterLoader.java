package register;

public interface RegisterLoader {

	/**
	 * Save register into database/file.
	 * 
	 * @param register
	 */
	void save(Register register);

	/**
	 * Load register from file or database into register.
	 * 
	 * @return register
	 */
	Register load();

}