package com.service;

import com.connection.MakeConnection;
import com.models.MedicalFacility;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			System.out.println("There was an error inserting data");
		}

	}

	public List<MedicalFacility> getAllFacilities() {

		String sql = "Select facility_id, name from Medical_Facilty";
		List<MedicalFacility> medicalFacilities = new ArrayList<MedicalFacility>();
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();
			MedicalFacility medicalFacility = new MedicalFacility();
			while( resultSet.next() ){
				medicalFacility.setName(resultSet.getString(2));
				medicalFacility.setFacility_id(resultSet.getLong(1));
				medicalFacilities.add(medicalFacility);
			}


		}catch( SQLException e ){
			System.out.println("There was an error fetching data from tables" );
		}
		return medicalFacilities;

	}

}
