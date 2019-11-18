package com.service;

import com.connection.MakeConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author neelparikh
 * @Date 2019-11-18
 */


public class DemoQueriesService {

	private Connection connection = MakeConnection.makeJDBCConnection();
	public void executeQuery(int choice)
	{
		String sql = "";
		if(choice == 1)
		{
			sql = "SELECT F.name, V.check_in, P.lname, P.fname, V.discharge_time, E.negative_exp, E.negative_exp_desc FROM PatientVisit V, Patients P, Medical_Facility F, Experience E WHERE V.phase = 2 AND V.pid = P.pid AND V.facility_id = F.facility_id AND V.exp_id IN (SELECT exp_id FROM Experience WHERE negative_exp IS NOT NULL)";
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				System.out.println("FacilityName\t\t Checkin\t\t lastName\t\t " +
				                   "firstname\t\tnegative Experience\t\t" +
				                   "negative experience Description");

				while( rs.next() )
				{
					System.out.println(rs.getString(1)+"\t\t"+
					                   rs.getDate(2)+"\t\t"+
					                   rs.getString(3)+"\t\t"+
					                   rs.getString(4)+"\t\t"+
					                   rs.getDate(5)+"\t\t"+
					                   rs.getInt(6)+"\t\t"+
					                   rs.getString(7));
				}
			}
			catch( SQLException e ) {
				e.printStackTrace();
			}
		}
		if(choice == 2)
		{
			sql = "SELECT F.name, V.check_in, P.lname, P.fname, V.discharge_time, E.negative_exp, E.negative_exp_desc FROM PatientVisit V, Patients P, Medical_Facility F, Experience E WHERE V.phase = 2 AND V.pid = P.pid AND V.facility_id = F.facility_id AND V.exp_id IN (SELECT exp_id FROM Experience WHERE negative_exp IS NOT NULL)";
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				System.out.println("FacilityName\t\t Checkin\t\t lastName\t\t " +
				                   "firstname\t\tnegative Experience\t\t" +
				                   "negative experience Description");

				while( rs.next() )
				{
					System.out.println(rs.getString(1)+"\t\t"+
					                   rs.getDate(2)+"\t\t"+
					                   rs.getString(3)+"\t\t"+
					                   rs.getString(4)+"\t\t"+
					                   rs.getDate(5)+"\t\t"+
					                   rs.getInt(6)+"\t\t"+
					                   rs.getString(7));
				}
			}
			catch( SQLException e ) {
				e.printStackTrace();
			}
		}
		}

}
