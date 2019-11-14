package com.service;

import com.connection.MakeConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		return response;
	}
}
