package com.service;

import com.GlobalConstants;
import com.connection.MakeConnection;
import com.models.Patient;
import com.models.PatientVisit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author neelparikh
 * @Date 2019-11-12
 */


public class PatientVisitService implements PatientVisitI {

	private Connection connection = MakeConnection.makeJDBCConnection();
	public boolean isCheckedIn(int pid, long facilityId) {
		String sql = "select * from PatientVisit where pid = ? and facility_id = ? " +
		             "and check_in IS NOT NULL and check_out IS NULL and phase = 0;";
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
				GlobalConstants.visitId = key;
			}
			connection.close();

		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
		return key;
	}

	public List<String> getCheckedInPatients(String name){
		String sql = "select lname from  patients where pid in(select pid from " +
		             "patientvisit where phase = 0 and facility_id in " +
		             "(select `facility_id` from `mf_service_dept` where(sd_id = " +
		             "(select primary_dept from Staff where name = ?))));";
		List<String> pids = new ArrayList<String>();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				pids.add(rs.getString(1));
			}
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
		return pids;
	}

	public void updateVitals(String lname, long vitalId){
		String sql = "update PatientVisit set vital_id = ? where pid = (select" +
		             " pid from patients where lname = ?) and phase = 0";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1,vitalId);
			ps.setString(2,lname);
			int x = ps.executeUpdate();

		}
		catch( SQLException e ) {
			e.printStackTrace();
		}

	}

	public void updateCheckOut(Date date, String lname, int phase){
		String sql = "";
		if(phase == 1) {
			sql = "update PatientVisit set check_out = ?, phase = ? where pid = (select pid from patients" +
			      " where lname = ?) and facility_id = (select " +
			      "facility_id from mf_service_dept where sd_id = (select " +
			      "primary_dept from Staff where name = ?));";
		}
		else if(phase == 2){
			sql = "update PatientVisit set treatment_start = ?, phase = ? where pid = (select pid from patients" +
			      " where lname = ?) and facility_id = (select " +
			      "facility_id from mf_service_dept where sd_id = (select " +
			      "primary_dept from Staff where name = ?));";
		}
		else if (phase == 3){
			sql = "update PatientVisit set discharge_time = ?, phase = ? where visit_id = ? and facility_id = (select " +
			      "facility_id from mf_service_dept where sd_id = (select " +
			      "primary_dept from Staff where name = ?));";
		}
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDate(1,date);
			ps.setInt(2,phase);
			if(phase == 3)
				ps.setLong(3,GlobalConstants.visitId);
			else
				ps.setString(3,lname);
			ps.setString(4, GlobalConstants.globalLastName);
			int x = ps.executeUpdate();

		}
		catch( SQLException e ) {
			e.printStackTrace();
		}

	}

	public List<String> getAllTreatedPatients()
	{
		String sql = "select lname from patients where pid = (select Distinct" +
		             "(pid) from PatientVisit where phase = 2 and exp_id IS NULL and facility_id " +
		             "= (select facility_id from mf_service_dept where sd_id =" +
		             " (select primary_dept from Staff where name = ?)));";

		List<String> patients = new ArrayList<String>();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1,GlobalConstants.globalLastName);
			ResultSet rs = ps.executeQuery();
			
			while( rs.next() )
			{
				patients.add(rs.getString(1));
			}



		}catch( SQLException e ) {
			e.printStackTrace();
		}
		return patients;
	}

	public int getVisitId(String lname)
	{
		String sql = "select visit_id from PatientVisit where phase = 2 and facility_id = " +
		              "(select facility_id from mf_service_dept where sd_id = " +
		              "(select primary_dept from Staff where name = ?)) " +
		              "and pid = (select pid from Patients where lname = ?);";
		PreparedStatement ps = null;
		int key = 0;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1,GlobalConstants.globalLastName);
			ps.setString(2,lname);
			ResultSet rs = ps.executeQuery();
			while( rs.next() )
			{
				key = rs.getInt(1);
			}
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
		return key;
	}

	public void updateExperience(int expId){
		String sql = "update PatientVisit set exp_id = ? where visit_id = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, expId);
			ps.setLong(2,GlobalConstants.visitId);
			ps.executeUpdate();

		}
		catch( SQLException e ) {
			e.printStackTrace();
		}

	}


}
