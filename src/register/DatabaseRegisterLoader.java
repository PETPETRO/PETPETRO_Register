package register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseRegisterLoader implements RegisterLoader {
	public static final String URL = "jdbc:mysql://localhost/register";
	public static final String USER = "root";
	public static final String PASSWORD = "root";
	public static final String SAVE = "insert into person (id,name,phoneNumber) values(?,?,?)";
	public static final String DELETE = "delete from person";
	public static final String SELECT = "select id, name,phoneNumber from person";

	public DatabaseRegisterLoader() {

	}

	@Override
	public void save(Register register) {

		try {
			store(register);
		} catch (SQLException e) {
			// e = new SQLException("Nieje mozne ulozit do databazy");
			e.getMessage();
			System.err.println(e);
			e.printStackTrace();
		}

	}

	private void store(Register register) throws SQLException {
		Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
		Statement stmt1 = con.createStatement();
		stmt1.execute(DELETE);
		stmt1.close();
		PreparedStatement stmt2 = con.prepareStatement(SAVE);
		int count = register.getCount();
		for (int i = 0; i < count; i++) {
			Person person = register.getPerson(i);
			stmt2.setInt(1, i + 1);
			stmt2.setString(2, person.getName());
			stmt2.setString(3, person.getPhoneNumber());
			stmt2.executeUpdate();
		}
		stmt2.close();
		con.close();

	}

	@Override
	public Register load() {
		try {
			return select();
		} catch (Exception e) {
			// e = new Exception("Nieje mozne nacitat z databazy");
			e.getMessage();
			System.err.println(e);
		}
		return null;

	}

	public Register select() throws Exception {
		Register register = new ListRegister();
		Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT);

		while (rs.next()) {
			String name = rs.getString(2);
			String phone = rs.getString(3);
			Person person = new Person(name, phone);
			register.addPerson(person);
		}
		rs.close();
		stmt.close();
		con.close();
		return register;
	}

}
