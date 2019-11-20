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

	public void addSymptom(String bCode, int severityId, Symptom symptom){

		String sql = "Insert into Symptoms values(?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, symptom.getSymptomId());
			ps.setString(2, symptom.getSymptomName());
			sql = "insert into associated values (?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, bCode);
			ps.setString(2, symptom.getSymptomId());
			ps.executeUpdate();

			sql = "Insert into has_scale values(?,?)";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, severityId);
			ps.setString(2, symptom.getSymptomId());
			ps.executeUpdate();
			connection.close();

		}
		catch( SQLException e ) {
			e.printStackTrace();
		}


	}

}
