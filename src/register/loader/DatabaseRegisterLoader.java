package register.loader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import register.Person;
import register.type.ListRegister;
import register.type.Register;

public class DatabaseRegisterLoader implements RegisterLoader {

	public static final String URL = "jdbc:mysql://localhost/register";
	public static final String USER = "root";
	public static final String PASSWORD = "root";
	// Queries
	public static final String SET = "SET SQL_SAFE_UPDATES = 0";
	public static final String SAVE = "insert into person (id,name,phoneNumber) values(?,?,?)";
	public static final String DELETE = "delete from person";
	public static final String SELECT = "select id, name,phoneNumber from person";
	public static final String CREATE_TABLE = "create table person (id int primary key,name varchar(30) not null,phoneNumber varchar(30) not null)";

	public DatabaseRegisterLoader() {
	}

	/**
	 * Save register to database.
	 */
	@Override
	public void save(Register register) {

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt1 = con.createStatement();
				PreparedStatement stmt2 = con.prepareStatement(SAVE);) {

			stmt1.execute(SET);
			stmt1.executeUpdate(DELETE);

			int count = register.getCount();
			for (int i = 0; i < count; i++) {
				Person person = register.getPerson(i);
				stmt2.setInt(1, i + 1);
				stmt2.setString(2, person.getName());
				stmt2.setString(3, person.getPhoneNumber());
				stmt2.executeUpdate();
			}

		} catch (MySQLSyntaxErrorException e) {
			try {
				DriverManager.getConnection(URL, USER, PASSWORD).createStatement().execute(CREATE_TABLE);
				save(register);
			} catch (SQLException e1) {
				e.getMessage();
			}

		} catch (SQLException e) {
			e.getMessage();
			System.err.println(e);
			e.printStackTrace();
		}
	}

	/**
	 * Load register from database to collection ArrayList.
	 */
	@Override
	public Register load() {
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(SELECT);) {
			Register register = new ListRegister();

			while (rs.next()) {
				String name = rs.getString(2);
				String phone = rs.getString(3);
				Person person = new Person(name, phone);
				register.addPerson(person);
			}
			return register;
		} catch (Exception e) {
			e = new Exception("Sorry, there is no database to load !");
			// System.err.println(e.getMessage());
			return null;
		}
	}
}
