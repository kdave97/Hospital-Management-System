package com.service;

import com.GlobalConstants;
import com.connection.MakeConnection;
import com.models.SymptomMetaData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author neelparikh
 * @Date 2019-11-14
 */


public class SymptomMetaDataService implements SymptomMetaDataI{

	private Connection connection = MakeConnection.makeJDBCConnection();
	public String isAssociated(String symptomId) {
		String sql = "select b_code from associated where sym_id = ?";
		PreparedStatement ps = null;
		String response = "";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1,symptomId);
			ResultSet resultSet = ps.executeQuery();
			while( resultSet.next() )
			{
				response = resultSet.getString(1);
			}
		}
		catch( SQLException e ) {
			e.printStackTrace();
			System.out.println("There was an error processing query");
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return response;
	}

	public void addSymptomMetaData(List<SymptomMetaData> symptomMetaDataList, int visitId){
		String sql = "Insert into patients_symptom_visit(sym_id, visit_id, " +
		             "severity, is_recurring, incident, pid, bodypart, duration_period) values(?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			for(SymptomMetaData symptomMetaData: symptomMetaDataList) {
				ps.setString(1, symptomMetaData.getSymId());
				ps.setInt(2, visitId);
				ps.setString(3, symptomMetaData.getSeverity());
				ps.setString(4, symptomMetaData.getIsRecurring());
				ps.setString(5, symptomMetaData.getCause());
				ps.setLong(6, GlobalConstants.globalPid);
				ps.setString(7, symptomMetaData.getBodyPart());
				ps.setString(8, symptomMetaData.getDuration());
				int x = ps.executeUpdate();
				System.out.println(x);
			}
			connection.close();


		}
		catch( SQLException e ) {
			e.printStackTrace();
		}


	}
}
