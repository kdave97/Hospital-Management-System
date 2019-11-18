package com.service;

import com.connection.MakeConnection;

import java.sql.*;

/**
 * @author neelparikh
 * @Date 2019-11-16
 */


public class ReferralService {
	private Connection connection = MakeConnection.makeJDBCConnection();
	public int insertReferral(int facilityId, int referrerId)
	{
		String sql = "Insert into Referral(facility_id,referrer_id) values" +
		             "(?,?)";
		int key = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, facilityId);
			ps.setInt(2,referrerId);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next())
			{
				key = rs.getInt(1);
			}
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
		return key;
	}

	public void insertExperienceReferral(int expid, int referralId)
	{
		String sql = "Insert into experience_referral(exp_id,referral_id)  values(?,?)";
		int key = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1,expid);
			ps.setInt(2,referralId);
			ps.executeUpdate();

		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
	}

	public void insertReferralReason(int referralId, int reasonId, String serviceName){
		String sql = "Insert into referral_reason(referral_id,reason_id,service_name) " +
		             "values(?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, referralId);
			ps.setInt(2, reasonId);
			ps.setString(3, serviceName);
			ps.executeUpdate();
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
	}
}
