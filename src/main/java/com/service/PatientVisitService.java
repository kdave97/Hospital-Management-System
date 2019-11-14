package com.service;

import com.connection.MakeConnection;
import com.models.PatientVisit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		int x = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, patientVisit.getPhase());
			ps.setDate(2, patientVisit.getCheckIn());
			ps.setString(3, patientVisit.getPriority());
			ps.setLong(4, patientVisit.getFacilityId());
			ps.setInt(5, patientVisit.getPid());
			x = ps.executeUpdate();

		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
		return x;
	}
}
