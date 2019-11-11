package com;

import com.models.MedicalFacility;
import com.service.MedicalFacilityI;
import com.service.MedicalFacilityService;

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
		System.out.println("Enter name");
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
		medicalFacilityI.addFacility(medicalFacility);

	}



}
