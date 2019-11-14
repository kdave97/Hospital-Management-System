package com.service;

import com.GlobalConstants;
import com.connection.MakeConnection;
import com.models.Patient;

import java.sql.*;

/**
 * @author neelparikh
 * @Date 2019-11-11
 */


public class PatientService implements PatientI {
	private Connection connection = MakeConnection.makeJDBCConnection();

	public int createPatient(Patient patient) {
		String sql = "Insert into patients(fname, lname, phone, dob) values(?,?,?,?)";
		ResultSet generatedKeys = null;
		int op = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,patient.getFname());
			ps.setString(2,patient.getLname());
			ps.setString(3,patient.getPhone());
			ps.setDate(4,patient.getDate());
			int rows_updated = ps.executeUpdate();
			if(rows_updated == 0)
				System.out.println("There was an error inserting patient");
			else
			{
				generatedKeys = ps.getGeneratedKeys();
				if(generatedKeys.next())
					op = generatedKeys.getInt(1);
			}
			connection.close();
		}
		catch( SQLException e ) {
			System.out.println("There was an error inserting patient");
		}
		return op;
	}

	public String getPatient(String lname, Date date, String city) {

		String sql = "Select pid,fname,lname from Patients where lname = ? and dob = ?";

		String fullName = "";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, lname);
			preparedStatement.setDate(2, date);

			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next())
			{
				String addressSql = "Select * from Address where pid = ? and city = ?";
				int pid = rs.getInt(1);
				PreparedStatement ps = connection.prepareStatement(addressSql);
				ps.setInt(1, pid);
				ps.setString(2, city);
				ResultSet resultSet = ps.executeQuery();
				if(resultSet.next()){
					fullName = rs.getString(2) +" "+ rs.getString(3);
					GlobalConstants.globalPid = pid;
				}
			}
			preparedStatement.close();
			connection.close();
		}
		catch( SQLException e ) {
			System.out.println("There was an error while fetching data.");
			e.printStackTrace();
		}

		return fullName;
	}


}
