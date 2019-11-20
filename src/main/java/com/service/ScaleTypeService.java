package com.service;

import com.connection.MakeConnection;
import com.models.ScaleType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author neelparikh
 * @Date 2019-11-15
 */


public class ScaleTypeService implements ScaleTypeI {
	private Connection connection = MakeConnection.makeJDBCConnection();
	public void addScale(ScaleType scaleType) {
		String sql = "Insert into scale_type(value) values(?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1,scaleType.getScaleType());
			int x = ps.executeUpdate();
			if(x == 1)
				System.out.println("Successful");
			else
				System.out.println("Unsuccessful");
			connection.close();
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}

	}
}
