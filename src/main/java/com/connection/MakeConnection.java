package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author neelparikh
 * @Date 2019-11-09
 */


public class MakeConnection {
	private static Connection connection = null;
	private static final String USERNAME = "neelp";
	private static final String PASSWORD = "root";


	public static Connection makeJDBCConnection() {


		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
		}

		try {
			// DriverManager: The basic service for managing a set of JDBC drivers.
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
			                                         USERNAME, PASSWORD);
			if (connection != null) {
			} else {
			}
		} catch ( SQLException e) {

		}
		return connection;
	}
}
