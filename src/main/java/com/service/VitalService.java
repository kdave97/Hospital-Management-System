package com.service;


import com.connection.MakeConnection;
import com.models.Vitals;

import java.sql.*;

/**
 * @author neelparikh
 * @Date 2019-11-14
 */


public class VitalService implements VitalsI {
	private Connection connection = MakeConnection.makeJDBCConnection();
	public int createVitals(Vitals vitals, String lname) {
		String sql = "insert into vitals(systolic, diastolic, temperature) values (?, ?, ?)";
		int id = 0;
		try {
			String generatedColumns[] = { "vital_id" };
			PreparedStatement ps = connection.prepareStatement(sql, generatedColumns);
			ps.setInt(1, vitals.getSystolic());
			ps.setInt(2, vitals.getDiastolic());
			ps.setInt(3, vitals.getTemperature());
			ps.executeUpdate();
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if(generatedKeys.next())
				id = generatedKeys.getInt(1);
			connection.close();


		}
		catch( SQLException e ) {
			e.printStackTrace();
		}

		return id;
	}
}
