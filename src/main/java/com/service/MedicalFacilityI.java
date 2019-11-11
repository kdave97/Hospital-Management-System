package com.service;

import com.models.MedicalFacility;

import java.util.ArrayList;

public interface MedicalFacilityI {
	public void addFacility(MedicalFacility medicalFacility);
	public ArrayList<MedicalFacility> getAllFacilities();
}
