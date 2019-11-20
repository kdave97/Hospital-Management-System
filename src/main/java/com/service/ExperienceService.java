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
		String sql = "Insert into Experience(discharge_status, treatment_given, negative_exp,negative_exp_description) " +
		             "values(?,?,?,?)";
		int expId = 0;
		try {
			String generatedColumns[] = { "exp_id" };
			PreparedStatement ps = connection.prepareStatement(sql, generatedColumns);
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
			connection.close();

		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return expId;
	}
	
	public void acknowledgemenntUpdate(int expId,  String reason)
	{
		String sql;
		if("".equals(reason)) {
			 sql = "update Experience set ack_status = 1 where exp_id = ?";
			 try {
					PreparedStatement ps =  connection.prepareStatement(sql);
					ps.setInt(1, expId);
					ps.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		else
		{
			sql = "update Experience set ack_status = 1, ack_reason = ?  where exp_id = ?";
			try {
				PreparedStatement ps =  connection.prepareStatement(sql);
				ps.setString(1, reason);
				ps.setInt(2, expId);
				ps.executeUpdate();
				connection.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
		
	}
}
