package com.service;

import com.connection.MakeConnection;
import com.models.MedicalFacility;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * @author neelparikh
 * @Date 2019-11-10
 */


public class MedicalFacilityService implements MedicalFacilityI {
	private Connection connection = MakeConnection.makeJDBCConnection();
	public int addFacility(MedicalFacility medicalFacility) {
		String sql = "Insert into "
	}

	public int updateFacility(int facility_id,
	                          MedicalFacility medicalFacilityI) {
		return 0;
	}

	public void deleteFacility(int facility_id) {

	}

	public ArrayList<MedicalFacility> getAllFacilities() {
		return null;
	}

	public MedicalFacility getMedicalFacility(int facility_id) {
		return null;
	}
}
