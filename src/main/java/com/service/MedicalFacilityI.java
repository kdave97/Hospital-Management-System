package com.service;

import com.models.MedicalFacility;

import java.util.ArrayList;

public interface MedicalFacilityI {
	public int addFacility(MedicalFacility medicalFacility);
	public int updateFacility(int facility_id, MedicalFacility medicalFacilityI);
	public void deleteFacility(int facility_id);
	public ArrayList<MedicalFacility> getAllFacilities();
	public MedicalFacility getMedicalFacility(int facility_id);
}
