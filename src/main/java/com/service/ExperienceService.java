package com.service;

import com.connection.MakeConnection;
import com.models.Experience;

import java.sql.*;

/**
 * @author neelparikh
 * @Date 2019-11-16
 */


public class ExperienceService {

	private Connection connection = MakeConnection.makeJDBCConnection();
	public int insertExperience(Experience experience)
	{
		String sql = "Insert into Experience(discharge_status, treatment_given, negative_exp,negative_exp_desc) " +
		             "values(?,?,?,?)";
		int expId = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, experience.getDischargeStatus());
			ps.setString(2, experience.getTreatmentGiven());
			ps.setInt(3, experience.getNegativeExperience());
			ps.setString(4, experience.getNegativeExperienceDescription());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next())
			{
				expId = rs.getInt(1);
			}
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
		return expId;
	}
}
