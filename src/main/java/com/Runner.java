package com;

import com.models.Address;
import com.models.MedicalFacility;
import com.models.Patient;
import com.service.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.Scanner;

/**
 * @author neelparikh
 * @Date 2019-11-11
 */


public class Runner {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
/*		System.out.println("Enter facility id");
		long facility_id = sc.nextLong();*/
/*		System.out.println("Enter name");
		String name = sc.nextLine();
		System.out.println("Enter Capacity");
		int capacity = sc.nextInt();
		System.out.println("Enter classification");
		sc.nextLine();
		String classification = sc.nextLine();
		System.out.println("Enter address");
		String address = sc.nextLine();
		MedicalFacility medicalFacility = new MedicalFacility();
		medicalFacility.setAddress(address);
		medicalFacility.setCapacity(capacity);
		medicalFacility.setClassification(classification);
//		medicalFacility.setFacility_id(facility_id);
		medicalFacility.setName(name);
		MedicalFacilityI medicalFacilityI = new MedicalFacilityService();
		medicalFacilityI.addFacility(medicalFacility);*/
		Patient patient = new Patient();
		patient.setFname("neel");
		patient.setLname("Parikh");
		Calendar calendar = Calendar.getInstance();
		calendar.set(1996, 10, 16);
		patient.setDate(new Date(calendar.getTime().getTime()));
		patient.setPhone("9999999999");
		PatientI patientI = new PatientService();
		int pid = patientI.createPatient(patient);

		Address address = new Address();
		address.setNumber(13);
		address.setCity("Raleigh");
		address.setCountry("USA");
		address.setState("North Carolina");
		address.setStreet("Avent Ferry");
		address.setPid(pid);
		AddressI addressI = new AddressService();
		addressI.insertAddress(address, pid);

	}



}
