package com.service;

import com.GlobalConstants;
import com.connection.MakeConnection;
import com.models.Experience;
import com.models.Patient;
import com.models.Referral;

import java.sql.*;

/**
 * @author neelparikh
 * @Date 2019-11-11
 */


public class PatientService implements PatientI {
	private Connection connection = MakeConnection.makeJDBCConnection();

	public int createPatient(Patient patient) {
		String sql = "Insert into patients(fname, lname, phone, dob) values(?,?,?,?)";
		ResultSet generatedKeys = null;
		int op = 0;
		try {
			String generatedColumns[] = { "pid" };
			PreparedStatement ps = connection.prepareStatement(sql, generatedColumns);
			ps.setString(1,patient.getFname());
			ps.setString(2,patient.getLname());
			ps.setString(3,patient.getPhone());
			ps.setDate(4,patient.getDate());
			int rows_updated = ps.executeUpdate();
			if(rows_updated == 0)
				System.out.println("There was an error inserting patient");
			else
			{
				
				generatedKeys = ps.getGeneratedKeys();
				if(generatedKeys.next())
					op = generatedKeys.getInt(1);
			}
			connection.close();
		}
		catch( SQLException e ) {
			System.out.println("There was an error inserting patient");
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
		return op;
	}

	public String getPatient(String lname, Date date, String city) {

		String sql = "Select pid,fname,lname from Patients where lname = ? and dob = ?";

		String fullName = "";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, lname);
			preparedStatement.setDate(2, date);

			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next())
			{
				String addressSql = "Select * from Address where pid = ? and city = ?";
				int pid = rs.getInt(1);
				PreparedStatement ps = connection.prepareStatement(addressSql);
				ps.setInt(1, pid);
				ps.setString(2, city);
				ResultSet resultSet = ps.executeQuery();
				if(resultSet.next()){
					fullName = rs.getString(2) +" "+ rs.getString(3);
					GlobalConstants.globalPid = pid;
				}
			}
			preparedStatement.close();
			connection.close();
		}
		catch( SQLException e ) {
			System.out.println("There was an error while fetching data.");
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

		return fullName;
	}
	
	public Experience getPatientExperience(int pid)
	{
		Experience experience = new Experience();
		Connection connection = MakeConnection.makeJDBCConnection();
		System.out.println(pid);
		String sql = "select  exp_id,  discharge_status, negative_exp, negative_exp_description,Treatment_given from Experience where exp_id = (select exp_id from PatientVisit where pid = ? and discharge_time is NOT NULL) and ack_status IS NULL";
		try {
			PreparedStatement  ps = connection.prepareStatement(sql);
			ps.setInt(1,  pid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				experience.setExpId(rs.getInt(1));
				experience.setDischargeStatus(rs.getString(2));
				experience.setNegativeExperience(rs.getInt(3));
				experience.setNegativeExperienceDescription(rs.getString(4));
				experience.setTreatmentGiven(rs.getString(5));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return experience;
		
	}
	
	public Referral getReferral(int pid, int expId) {
		Connection connection = MakeConnection.makeJDBCConnection();
		Referral referral = new Referral();
		String sql = "select facility_id, referrer_id from Referral where  referral_id"
				+ " = (select referral_id from experience_referral where exp_id =?)";
		try {
			PreparedStatement  ps = connection.prepareStatement(sql);
			ps.setInt(1, expId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				referral.setFacilityId(rs.getInt(1));
				referral.setReferrerId(rs.getInt(2));
			}
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return referral;
		
		}


}
