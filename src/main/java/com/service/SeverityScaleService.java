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


public class SeverityScaleService {
	private Connection connection = MakeConnection.makeJDBCConnection();
	public String getSeverityScale(String symptomId)
	{
		String sql = "select value from Scale_type where scale_id = (select " +
		             "scale_id from `has_scale` where sym_id = ?);";
		String scale = "";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, symptomId);
			ResultSet rs = ps.executeQuery();
			while( rs.next() ){
				scale = rs.getString(1);
			}
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
		return scale;
	}

}
