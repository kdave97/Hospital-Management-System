package com.service;

import com.connection.MakeConnection;
import com.models.MedicalFacility;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author neelparikh
 * @Date 2019-11-10
 */


public class MedicalFacilityService implements MedicalFacilityI {
	private Connection connection = MakeConnection.makeJDBCConnection();
	private Logger logger = Logger.getLogger(MedicalFacilityService.class);
	public void addFacility(MedicalFacility medicalFacility) {
		String sql = "Insert into Medical_Facility(name, capacity, classification, address) values (?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1,medicalFacility.getName());
			ps.setInt(2,medicalFacility.getCapacity());
			ps.setString(3,medicalFacility.getClassification());
			ps.setString(4, medicalFacility.getAddress());
			int rows_affected = ps.executeUpdate();
			logger.info(rows_affected +" inserted");
			connection.close();
		}
		catch( SQLException e ) {
			logger.error("There was an error inserting data");
			e.printStackTrace();
		}

	}

	public ArrayList<MedicalFacility> getAllFacilities() {
		return null;
	}

}
