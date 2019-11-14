package com.service;

import com.models.Patient;

import java.sql.Date;

public interface PatientI {

public int createPatient(Patient patient);
public String getPatient(String lname, Date date, String city);
}
