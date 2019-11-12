package com.service;

import com.models.MedicalFacility;

import java.util.List;

public interface MedicalFacilityI {
	public void addFacility(MedicalFacility medicalFacility);
	public List<MedicalFacility> getAllFacilities();
}
