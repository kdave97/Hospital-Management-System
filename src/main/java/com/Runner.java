package com;

import com.models.MedicalFacility;
import com.models.PatientVisit;
import com.models.Symptom;
import com.models.SymptomMetaData;
import com.service.*;
import com.util.Utility;
import com.util.Validation;

import java.sql.Date;
import java.util.*;

/**
 * @author neelparikh
 * @Date 2019-11-11
 */


public class Runner {

	private static Scanner sc = new Scanner(System.in);
	private static Map<Integer, String> symptomMetaDataMap = new HashMap<Integer, String>();
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
/*		Patient patient = new Patient();
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
		addressI.insertAddress(address, pid);*/

		/*Calendar calendar = Calendar.getInstance();
		calendar.set(1996, 10, 16);

		System.out.println(new PatientService().getPatient("Parikh",
		                                                   new Date(calendar.getTime().getTime()), "Raleigh"));
		System.out.println(new PatientService().getPatient("Parikh",
		                                                   new Date(calendar.getTime().getTime()), "Ahmedabad"));
		calendar.set(1997, 10, 16);
		System.out.println(new PatientService().getPatient("Parikh",
		                                                   new Date(calendar.getTime().getTime()), "Ahmedabad"));
		System.out.println(new PatientService().getPatient("Neel",
		                                                   new Date(calendar.getTime().getTime()), "Raleigh"));
*/

		signIn();
		Map<Integer, String> symptomCountMap = displayAllSymptoms();

		String str = sc.nextLine();
		while(!("q").equals(str)) {
			SymptomMetaData symptomMetaData = new SymptomMetaData();
			String bodyPart = "";
			try {
				int choice = Integer.parseInt(str);
				String symptomId = symptomCountMap.get(choice);
				String resp = sc.nextLine();

				while(!("d").equals(resp)) {
					bodyPart = displaySymptomMetadata(symptomId);
					resp = sc.nextLine();
					int key = Integer.parseInt(resp);
					displayMenu(symptomMetaData,key);
				}


			}
			catch( Exception e )
			{
				System.out.println("Enter number without any spaces");
			}
			if(!("").equals(bodyPart))
				symptomMetaData.setBodyPart(bodyPart);
			displayAllSymptoms();
			str = sc.nextLine();
		}
		new PatientVisitService().admitPatient(setPatientVisit());

	}

	private static void signIn() {

		displayAllFacilities();
		System.out.println("Enter Facility Id: ");
		long facility_id = sc.nextLong();
		sc.nextLine();
		System.out.println("Enter Last name: ");
		String lname = sc.nextLine();
		//Date of birth
		System.out.println("Enter Date of birth in (\"mm-dd-yyyy\") format");
		String date = sc.nextLine();
		while( ! Validation.validateDate(date) ) {
			System.out.println("Invalid Date format.. Please Enter again");
			date = sc.nextLine();
		}
		Calendar calendar = Calendar.getInstance();
		int[] dob = Utility.parseDate(date, "-");
		calendar.set(dob[2], dob[0], dob[1]);
		Date sqlDate = new Date(calendar.getTime().getTime());

		System.out.println("Enter city:");
		String city = sc.nextLine();
		System.out.println("Are you a Patient? ");
		String isPatient = sc.nextLine();

		if(("yes").equals(isPatient)) {
			PatientI patientI = new PatientService();
			String response = patientI.getPatient(lname, sqlDate, city);
			while( "".equals(response) )
			{
				System.out.println("Invalid Credentials.. Please try again");
				signIn();
			}
			GlobalConstants.globalLastName = response;
			GlobalConstants.globalRole = "patient";
			while(new PatientVisitService().isCheckedIn(GlobalConstants.globalPid, facility_id))
			{
				System.out.println("You are already checked-in to this faciltiy please select another facilty ID");
				facility_id = sc.nextLong();
			}
			GlobalConstants.globalFacilityId = facility_id;


		}
		else
		{
			//do staff login here
		}
	}

	private static void displayAllFacilities()
	{
		List<MedicalFacility> medicalFacilities = new MedicalFacilityService().getAllFacilities();
		System.out.println("Id\t\t\tName");
		for(MedicalFacility medicalFacility: medicalFacilities)
		{
			System.out.println(medicalFacility.getFacility_id()+"\t\t"+medicalFacility.getName());
		}
	}

	private static Map<Integer, String> displayAllSymptoms()
	{
		// CHeck if the patient has already checked in or not before displaying the list
		List<Symptom> symptoms = new SymptomService().getAllSymptoms();
		System.out.println("Id\t\t\tSymptom Name");
		int count = 1;
		Map<Integer, String> symptomCountMap = new HashMap<Integer, String>();
		for(Symptom symptom: symptoms){
			System.out.println(count+"\t\t\t" + symptom.getSymptomName());
			symptomCountMap.put(count, symptom.getSymptomId());
			count++;
		}
		System.out.println("press \"q\" when done.");
		System.out.println("press \"n\" to enter new symptom.");
		return symptomCountMap;
	}

	private static String displaySymptomMetadata(String symptomId){
		String response = new SymptomMetaDataService().isAssociated(symptomId);
		setSymptomMetaDataMap();
		if(response.equals(""))
		{
			System.out.println("1\t\tBody Part");
		}


			System.out.println("2\t\tDuration");
			System.out.println("3\t\tRecurring");
			System.out.println("4\t\tSeverity");
			System.out.println("5\t\tCause");
			System.out.println("press \"d\" when done");
			return response;

	}

	private static void displayMenu(SymptomMetaData symptomMetaData, int key){

		String response = symptomMetaDataMap.get(key);
		System.out.println("Enter " + response);

		switch( key ) {
			case 1:
				String resp = sc.nextLine();
				symptomMetaData.setBodyPart(resp);
				break;
			case 2:
				resp = sc.nextLine();
				symptomMetaData.setDuration(resp);
				break;
			case 3:
				resp = sc.nextLine();
				symptomMetaData.setIsRecurring(resp);
				break;
			case 4:
				resp = sc.nextLine();
				symptomMetaData.setSeverity(resp);
				break;
			case 5:
				resp = sc.nextLine();
				symptomMetaData.setCause(resp);
				break;
		}
	}
	private static void setSymptomMetaDataMap(){
		symptomMetaDataMap.put(1, "Body Part");
		symptomMetaDataMap.put(2, "Duration");
		symptomMetaDataMap.put(3, "isRecurring");
		symptomMetaDataMap.put(4, "Severity");
		symptomMetaDataMap.put(5, "Cause");
	}

	private static PatientVisit setPatientVisit(){
		PatientVisit patientVisit = new PatientVisit();
		patientVisit.setPid(GlobalConstants.globalPid);
		patientVisit.setCheckIn(new Date(Calendar.getInstance().getTime().getTime()));
		patientVisit.setPhase(0);
		patientVisit.setFacilityId(GlobalConstants.globalFacilityId);
		return patientVisit;
	}



}