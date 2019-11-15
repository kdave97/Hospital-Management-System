package com.service;

import com.connection.MakeConnection;
import com.models.PatientVisit;

import java.sql.*;

/**
 * @author neelparikh
 * @Date 2019-11-12
 */


public class PatientVisitService implements PatientVisitI {

	private Connection connection = MakeConnection.makeJDBCConnection();
	public boolean isCheckedIn(int pid, long facilityId) {
		String sql = "select * from PatientVisit where pid = ? and facility_id = ? " +
		             "and check_in <> NULL and check_out = NULL";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1,pid);
			ps.setLong(2,facilityId);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next())
			{
				return true;
			}
			connection.close();
		}
		catch( SQLException e ) {
			System.out.println("there was an error fetching data");
			e.printStackTrace();
		}
		return false;

	}

	public int admitPatient(PatientVisit patientVisit)
	{
		String sql = "Insert into PatientVisit(phase, check_in, priority, facility_id, pid) values(?,?,?,?,?)";
		int key = 0;
		ResultSet generatedKeys = null;
		try {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, patientVisit.getPhase());
			ps.setDate(2, patientVisit.getCheckIn());
			ps.setString(3, patientVisit.getPriority());
			ps.setLong(4, patientVisit.getFacilityId());
			ps.setInt(5, patientVisit.getPid());
			int rows_updated = ps.executeUpdate();
			if(rows_updated == 0)
				System.out.println("There was an error inserting patient");
			else
			{
				generatedKeys = ps.getGeneratedKeys();
				if(generatedKeys.next())
					key = generatedKeys.getInt(1);
			}
			connection.close();

		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
		return key;
	}
}
