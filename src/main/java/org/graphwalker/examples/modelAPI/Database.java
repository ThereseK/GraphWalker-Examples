package org.graphwalker.examples.modelAPI;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.graphwalker.exceptions.InvalidDataException;
import org.graphwalker.generators.PathGenerator;

public class Database extends org.graphwalker.multipleModels.ModelAPI {

	public Connection connection = null;

	public Database(File model, boolean efsm, PathGenerator generator,
			boolean weight) {

		super(model, efsm, generator, weight);

	}

	/**
	 * This method implements the Edge 'e_add_columnT1'
	 *
	 * @throws SQLException
	 * @throws InvalidDataException
	 * @throws NumberFormatException
	 *
	 */

	public void e_add_columnT1() throws SQLException, NumberFormatException {
			Statement statement = connection.createStatement();
			statement.execute("ALTER TABLE Employer ADD address varchar (255)");
	}


	/**
	 * This method implements the Edge 'e_connectH2'
	 *
	 */
	public void e_connectH2() throws Exception {
        Class.forName("org.h2.Driver");
		connection = DriverManager
				.getConnection("jdbc:h2:mem:~/test", "sa", "");
    }

	/**
	 * This method implements the Edge 'e_create_T1'
	 *
	 * @throws SQLException
	 * @throws InvalidDataException
	 * @throws NumberFormatException
	 *
	 */
	public void e_create_T1() throws SQLException, NumberFormatException,
			InvalidDataException {
			Statement statement = connection.createStatement();
			statement
					.execute("CREATE TABLE Employer (name varchar (255), city varchar (255))");
	}

	/**
	 * This method implements the Edge 'e_create_T2'
	 *
	 * @throws SQLException
	 * @throws InvalidDataException
	 * @throws NumberFormatException
	 *
	 */
	public void e_create_T2() throws SQLException, NumberFormatException {
			Statement statement = connection.createStatement();
			statement.execute("CREATE TABLE Team (name varchar (255) )");
	}

	/**
	 * This method implements the Edge 'e_delete_columnT1'
	 *
	 * @throws SQLException
	 *
	 */

	public void e_delete_columnT1() throws SQLException {
		Statement statement = connection.createStatement();
		statement.execute("ALTER TABLE Employer DROP address");
	}

	/**
	 * This method implements the Edge 'e_delete_rowT1'
	 *
	 * @throws SQLException
	 *
	 */

	public void e_delete_rowT1() throws SQLException {
		Statement statement = connection.createStatement();
		statement.execute("DELETE FROM Employer WHERE name = 'Therese'");
	}

	/**
	 * This method implements the Edge 'e_delete_T1'
	 *
	 * @throws SQLException
	 *
	 */
	public void e_delete_T1() throws SQLException {
		Statement statement = connection.createStatement();
		statement.execute("DROP TABLE Employer");
	}

	/**
	 * This method implements the Edge 'e_delete_T2'
	 *
	 * @throws SQLException
	 *
	 */
	public void e_delete_T2() throws SQLException {
		Statement statement = connection.createStatement();
		statement.execute("DROP TABLE Team");
	}

	/**
	 * This method implements the Edge 'e_insert_rowT1'
	 *
	 * @throws SQLException
	 *
	 */

	public void e_insert_rowT1() throws SQLException {
		Statement statement = connection.createStatement();
		statement
				.execute("INSERT INTO Employer (name, city) VALUES ('Alice', 'Stockholm')");
	}

	/**
	 * This method implements the Vertex 'v_H2Connected'
	 *
	 * @throws SQLException
	 *
	 */
	public void v_H2Connected() throws SQLException {
		Statement statement = connection.createStatement();
		statement.execute("DROP TABLE IF EXISTS TEAM");
		statement.execute("DROP TABLE IF EXISTS EMPLOYER");

	}

	/**
	 * This method implements the Vertex 'v_check_T1'
	 * 
	 * @throws Exception
	 * 
	 */
	public void v_check_T1() throws Exception {
		Statement statement = connection.createStatement();
		ResultSet result = statement
				.executeQuery("SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'EMPLOYER'");
		if (!result.next()) {
			throw new Exception("no table called Employer");
		}
	}

	/**
	 * This method implements the Vertex 'v_check_T2'
	 * 
	 * @throws Exception
	 * 
	 */
	public void v_check_T2() throws Exception {
		Statement statement = connection.createStatement();
		ResultSet result = statement
				.executeQuery("SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'TEAM'");
		if (!result.next()) {
			throw new Exception("no table called Team");
		}
	}

	/**
	 * This method implements the Vertex 'v_check_columnsT1'
	 *
	 * @throws Exception
	 *
	 */
	public void v_check_columnsT1() throws Exception {
		Statement statement = connection.createStatement();
		ResultSet result = statement
				.executeQuery("SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'EMPLOYER' AND COLUMN_NAME = 'Name'");
		if (result == null) {
			throw new Exception("no columns in T1");
		}
	}

	/**
	 * This method implements the Vertex 'v_check_rowsT1'
	 *
	 * @throws Exception
	 *
	 */
	public void v_check_rowsT1() throws Exception {
		Statement statement = connection.createStatement();
		ResultSet result = statement
				.executeQuery("SELECT * FROM EMPLOYER WHERE NAME = 'Alice'");
		if (result == null) {
			throw new Exception("no rows in T1");
		}
	}
}
