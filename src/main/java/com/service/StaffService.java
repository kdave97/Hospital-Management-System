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
		System.out.print(date.toString());
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


	public boolean isAllowed(String name ){
		String sql = "select count(*) from Staff where name = ? " +
		             "and primary_dept in(select sd_id from specializes_in " +
		             "where b_code in (select b_code from BodyParts where " +
		             "b_code in(select bodypart from patients_symptom_visit " +
		             "where pid in (select pid from patients where lname = ?) and visit_id = ?)))";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, GlobalConstants.globalLastName);
			ps.setString(2, name);
			ps.setLong(3, GlobalConstants.visitId);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next())
			{
				return true;
			}
			connection.close();

		}
		catch( SQLException e ) {
			e.printStackTrace();
		}

		return false;
	}
}
