package com.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.GlobalConstants;
import com.connection.MakeConnection;

public class StaffService implements StaffI {

	private Connection connection = MakeConnection.makeJDBCConnection();



	public boolean getStaff(String name, Date date) {

		String sql = "Select name, DOB from Staff where name = ? and DOB = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setDate(2, date);

			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next())
			{
				
				return true;
			}
			preparedStatement.close();
			connection.close();
		}
		catch( SQLException e ) {
			System.out.println("There was an error while fetching data.");
			e.printStackTrace();
		}

		return false;
	}
}
