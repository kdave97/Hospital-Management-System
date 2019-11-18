package com.connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author neelparikh
 * @Date 2019-11-09
 */


public class MakeConnection {
	private final static Logger log = Logger.getLogger(MakeConnection.class);
	private static Connection connection = null;
	private static final String USERNAME = "root";
	private static final String PASSWORD = "neel1996";


	public static Connection makeJDBCConnection() {


		try {
			Class.forName("com.mysql.jdbc.Driver");
			log.info("Congrats - Seems your MySQL JDBC Driver Registered!");
		} catch (ClassNotFoundException e) {
			log.error("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
		}

		try {
			// DriverManager: The basic service for managing a set of JDBC drivers.
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalManagement",
			                                         USERNAME, PASSWORD);
			if (connection != null) {
				log.info("Connection Successful! Enjoy. Now it's time to push data");
			} else {
				log.error("Failed to make connection!");
			}
		} catch ( SQLException e) {
			log.error("MySQL Connection Failed!");

		}
		return connection;
	}
}
