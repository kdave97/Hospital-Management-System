package com.service;

import com.connection.MakeConnection;
import com.models.Symptom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author neelparikh
 * @Date 2019-11-13
 */


public class SymptomService implements SymptomI {

	private Connection connection = MakeConnection.makeJDBCConnection();
	public List<Symptom> getAllSymptoms() {

		String sql = "Select * from symptoms";
		List<Symptom> symptoms = null;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			symptoms = convertResultSetToArrayList(rs);
		}
		catch( SQLException e ) {
//			e.printStackTrace();
			System.out.println("There was an error fetching data");
		}
		return symptoms;
	}

	private List<Symptom> convertResultSetToArrayList(ResultSet resultSet) throws SQLException {
		List<Symptom> symptoms = new ArrayList<Symptom>();
		while( resultSet.next() ){
			Symptom symptom = new Symptom();
			symptom.setSymptomId(resultSet.getString(1));
			symptom.setSymptomName(resultSet.getString(2));
			symptoms.add(symptom);
		}
		return symptoms;
	}
}
