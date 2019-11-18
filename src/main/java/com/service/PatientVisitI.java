package com.service;

import com.models.PatientVisit;

import java.sql.Date;
import java.util.List;

public interface PatientVisitI {

	public boolean isCheckedIn(int pid, long facilityId);
	public int admitPatient(PatientVisit patientVisit);
	public List<String> getCheckedInPatients(String name);
	public void updateVitals(String lname, long vitalId);
	public void updateCheckOut(Date date, String lname, int phase);

}
