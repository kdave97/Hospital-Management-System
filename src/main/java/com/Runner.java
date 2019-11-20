package com;

import com.connection.MakeConnection;
import com.models.*;
import com.service.*;
import com.util.Utility;
import com.util.Validation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLOutput;
import java.util.*;

/**
 * @author neelparikh
 * @Date 2019-11-11
 */


public class Runner {
	private static int negative_exp_code;
	private static String negative_exp_reason;
	private static String discharge_status;
	private static String treatment_given;
	private static int referral_facility_id;
	private static int referrer_id;
	private static int referral_reason_code;
	private static String referral_service_name;

	private static Scanner sc = new Scanner(System.in);
	private static Map<Integer, String> symptomMetaDataMap =
			new HashMap<Integer, String>();

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
		                                                   new Date(calendar
		                                                   .getTime().getTime
		                                                   ()), "Raleigh"));
		System.out.println(new PatientService().getPatient("Parikh",
		                                                   new Date(calendar
		                                                   .getTime().getTime
		                                                   ()), "Ahmedabad"));
		calendar.set(1997, 10, 16);
		System.out.println(new PatientService().getPatient("Parikh",
		                                                   new Date(calendar
		                                                   .getTime().getTime
		                                                   ()), "Ahmedabad"));
		System.out.println(new PatientService().getPatient("Neel",
		                                                   new Date(calendar
		                                                   .getTime().getTime
		                                                   ()), "Raleigh"));
*/
		home();


	}


	public static void home() {

		System.out.println("Welcome");
		System.out.println("1) Sign In  2) Sign UP 3)Demo Queries 4)Exit ");
		System.out.println("Select a choice");

		int choice = sc.nextInt();

		switch( choice ) {

			case 1:
				SignIn_disp();
				break;
			case 2:
				SignUp();
				break;
		/*
		 * case 3: PatientAcknowledgement(); break;
		 */
//	        case 3: DemoQueries();
//	                break;
			case 4:
				home();
				break;
		}

	}

	private static void PatientAcknowledgement() {
		// TODO Auto-generated method stub
		PatientService patientService = new PatientService();
		Experience experience = patientService.getPatientExperience(GlobalConstants.globalPid);
		System.out.println("Discharge Status:\t" + experience.getDischargeStatus());
		System.out.println("Treatment Given:\t" + experience.getTreatmentGiven());
		if(experience.getNegativeExperience() == 1) {
			System.out.println("Negative Experience: Misdiagnosis");
		}
		else if(experience.getNegativeExperience() == 2) {
			System.out.println("Negative Experience: Patient acquired an infection during hospital stay.");
		}
		int expId = experience.getExpId();
		System.out.println("Negative Experience Description:" + experience.getNegativeExperienceDescription());
		if("Referred".equals(experience.getDischargeStatus())){
			
			Referral referral = patientService.getReferral(GlobalConstants.globalPid, expId);
			System.out.println("FacilityID:\t"+ referral.getFacilityId());
			System.out.println("ReferrerId:\t" + referral.getReferrerId());
		}
		

		System.out.println("Do you acknowledge?");
		System.out.println("1) Yes 2) No 3) Go back");
		System.out.println("Select a choice");

		int choice = sc.nextInt();
		
		if(choice == 1) {
			new ExperienceService().acknowledgemenntUpdate(expId, "");
		}
		else {
			sc.nextLine();
			String reason = sc.nextLine();
			new ExperienceService().acknowledgemenntUpdate(expId, reason);
		}
		patientRouting();
		
	}


	public static void DemoQueries() {

		System.out.println("*********DEMO QUERIES**********");

		System.out.println("1) Find all patients that were discharged but had negative experiences at any facility, list their names,facility, check-in date, discharge date and negative experiences");
		System.out.println("2) Find facilities that did not have a negative experience for a specific period (to be given).");
		System.out.println("3) For each facility, find the facility that is sends the most referrals to.");
		System.out.println("4) Find facilities that had no negative experience for patients with cardiac symptoms");
		System.out.println("5) Find the facility with the most number of negative experiences (overall i.e. of either kind)");
		System.out.println("6) Find each facility, list the patient encounters with the top five longest check-in phases (i.e. time from begin check-in to when treatment phase begins (list the name of patient, date, facility, duration and list of symptoms).");
		System.out.println("7) Go Back");
		System.out.println("Select a choice");

		int choice=sc.nextInt();
		switch(choice){

			case 1: // Execute Query 1
				new DemoQueriesService().executeQuery(1);
				break;
			case 2: // Execute Query 2
				home();
				break;
			case 3: // Execute Query 3
				home();
				break;
			case 4: // Execute Query 4
				home();
				break;
			case 5: // Execute Query 5
				home();
				break;
			case 6: // Execute Query 6
				home();
				break;
			case 7: home();
				break;
		}
	}

	public static void SignIn_disp() {
		System.out.println("Welcome to Sign In");
		System.out.println("1) Sign In 2) Go back");
		int ch = sc.nextInt();
		switch( ch ) {

			case 1:
				signIn();
				break;
			case 2:
				home();
				break;
		}
	}


	private static void SignUp() {
		sc.nextLine();
		System.out.println("Enter first name: ");
		String su_fname = sc.nextLine();
		System.out.println("Enter last name: ");
		String su_lname = sc.nextLine();
		System.out.println("Enter Date of birth in (\"mm-dd-yyyy\") format");
		String date = sc.nextLine();
		while( ! Validation.validateDate(date) ) {
			System.out.println("Invalid Date format.. Please Enter again");
			date = sc.nextLine();
		}

		System.out.println("Enter Apartment number: ");
		int su_apt_num = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter street name: ");
		String su_street = sc.nextLine();
		System.out.println("Enter city: ");
		String su_city = sc.nextLine();
		System.out.println("Enter state: ");
		String su_state = sc.nextLine();
		System.out.println("Enter country: ");
		String su_country = sc.nextLine();
		System.out.println("Enter Phone Number: ");
		String su_phone = sc.nextLine();


		System.out.println("Press 1 to Sign Up or Press 2 to return to Home " +
		                   "Menu");
		int ch = sc.nextInt();
		switch( ch ) {

			case 1: //Push data to database
				Patient patient = new Patient();
				patient.setFname(su_fname);
				patient.setLname(su_lname);
				Calendar calendar = Calendar.getInstance();
				int[] dob = Utility.parseDate(date, "-");
				calendar.set(dob[2], dob[0] - 1, dob[1]);
				Date sqlDate = new Date(calendar.getTime().getTime());
				patient.setDate(sqlDate);
				patient.setPhone(su_phone);
				PatientI patientI = new PatientService();
				int pid = patientI.createPatient(patient);

				Address address = new Address();
				address.setNumber(su_apt_num);
				address.setCity(su_city);
				address.setCountry(su_country);
				address.setState(su_state);
				address.setStreet(su_street);
				address.setPid(pid);
				AddressI addressI = new AddressService();
				addressI.insertAddress(address, pid);

				System.out.println("Account Created! ");
				SignIn_disp();
				break;
			case 2:
				home();
				break;
		}
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
		calendar.set(dob[2], dob[0] - 1, dob[1]);
		Date sqlDate = new Date(calendar.getTime().getTime());

		System.out.println("Enter city:");
		String city = sc.nextLine();
		System.out.println("Are you a Patient? ");
		String isPatient = sc.nextLine();

		if( ("yes").equals(isPatient) ) {
			PatientI patientI = new PatientService();
			String response = patientI.getPatient(lname, sqlDate, city);
			while( "".equals(response) ) {
				System.out.println("Invalid Credentials.. Please try again");
				signIn();
			}
			GlobalConstants.globalLastName = response;
			GlobalConstants.globalRole = "patient";
			while( new PatientVisitService().isCheckedIn(GlobalConstants.globalPid, facility_id) ) {
				System.out.println("You are already checked-in to this " +
				                   "faciltiy please select another facilty " +
				                   "ID");
				facility_id = sc.nextLong();
			}
			GlobalConstants.globalFacilityId = facility_id;
			patientRouting();
		} else {
			StaffI staffI = new StaffService();
			boolean response = staffI.getStaff(lname, sqlDate);
			if( ! response ) {
				System.out.println("Invalid Credentials.. Please try again");
				signIn();
			}
			GlobalConstants.globalLastName = lname;
			GlobalConstants.globalRole = "staff";
			GlobalConstants.globalFacilityId = facility_id;
			Staffmenu();
		}
	}

	public static void Staffmenu() {

		System.out.println("Welcome Staff!");
		System.out.println("1) Checked-In patient list 2) Treated Patient List" +
		                   " 3)Add Symptoms 4) Add Severity Scale 5) Add " +
		                   "Assesment Rule 6)Go back ");
		System.out.println("Select a choice");

		int choice = sc.nextInt();

		switch( choice ) {

			case 1:
				checkedIn();
				Staffmenu();
				break;
			case 2:
				Map<Integer, String> integerStringMap = TreatedPatient();

				Staffmenu();
				break;
			case 3:
				addSymptom();
				break;
			case 4:
				String finalScale = "";
				addSeve(finalScale);
				break;
			case 5:
				AddAssessmentRule();
				break;
/*	        case 6: SignIn();
	        break;*/
		}
	}

	private static Map<Integer, String> TreatedPatient() {
		PatientVisitService patientVisitService = new PatientVisitService();
		List<String> patients = patientVisitService.getAllTreatedPatients();
		Map<Integer, String> integerStringMap = new HashMap<Integer, String>();
		int count = 1;
		for(String lname: patients)
		{
			System.out.println(count+"\t\t"+lname);
			integerStringMap.put(count,lname);
			count++;
		}
		System.out.println("1) Check Out 2) Go back ");
		System.out.println("Select a choice");

		int choice=sc.nextInt();

		switch(choice){

			case 1:
				GlobalConstants.visitId = new PatientVisitService().getVisitId(integerStringMap.get(choice));
				StaffPatientReport();
				break;
			case 2: Staffmenu();
				break;
		}
		return integerStringMap;
	}

	private static void AddAssessmentRule() {
		sc.nextLine();
		Map<Integer, String> symptomCountMap = displayAllSymptoms();
		System.out.println("Select the symptom");
		String resp = sc.nextLine();
		List<BodySymptomRule> bodySymptomRules =
				new ArrayList<BodySymptomRule>();
		int ruleId = new RuleService().insertRule(1);
		while( ! ("d").equals(resp) ) {
			BodySymptomRule bodySymptomRule = new BodySymptomRule();
			try {
				String symptomId = symptomCountMap.get(Integer.parseInt(resp));
				System.out.println(symptomId);
				String scale =
						new SeverityScaleService().getSeverityScale(symptomId);
				System.out.println("Enter scale in " + scale);
				String severity = sc.nextLine();
				System.out.println("Enter </>/= for the severity");
				String cmp = sc.nextLine();
				String bCode =
						new SymptomMetaDataService().isAssociated(symptomId);
				System.out.println(bCode);
				if( "".equals(bCode) ) {
					bCode = "ALL";
				}
				System.out.println(bCode);
				System.out.println(ruleId);

				bodySymptomRule.setbCode(bCode);
				bodySymptomRule.setRuleId(ruleId);
				bodySymptomRule.setSeverity(severity);
				bodySymptomRule.setSymbol(cmp);
				bodySymptomRule.setSymptomId(symptomId);
				bodySymptomRules.add(bodySymptomRule);
			}
			catch( Exception E ) {
				System.out.println("There was an error parsing data");
			}

			displayAllSymptoms();
			resp = sc.nextLine();
		}
		new BodySymptomRuleService().insertAssessmentRule(bodySymptomRules);
		System.out.println("enter priorityId1 for the rules");
		int priorityId = sc.nextInt();
		new RuleService().updatePriorityId(priorityId, ruleId);
		Staffmenu();

	}

	public static void addSymptom() {
		sc.nextLine();
		System.out.println("Enter Symptom id: ");
		String sym_id = sc.nextLine();
		System.out.println("Enter Symptom Name: ");
		String sym_name = sc.nextLine();
		System.out.println("Enter Body Part");
		String body_part = sc.nextLine();
		//display the type of severity to choose from
		List<String> scale = new SeverityScaleService().getAllSeverityScales();
		int count = 1;
		for( String eachScale : scale ) {
			System.out.println(count + ". " + eachScale);
			count++;
		}
		System.out.println("Enter Severity");
		int severity = sc.nextInt();
		System.out.println("1) Record 2)Go back ");
		System.out.println("Select a choice");
		Symptom symptom = new Symptom();
		symptom.setSymptomName(sym_name);
		symptom.setSymptomId(sym_id);
		int choice = sc.nextInt();

		switch( choice ) {

			case 1:
				new SymptomService().addSymptom(body_part, severity, symptom);
				break;
			case 2:
				Staffmenu();
				break;
		}
		Staffmenu();
	}

	public static void addSeve(String finalScale) {
		System.out.println("1) Add level for this scale 2) No more levels 3)Go" +
		                   " back ");
		System.out.println("Select a choice");

		int choice = sc.nextInt();

		sc.nextLine();
		ScaleType scaleType = new ScaleType();
		switch( choice ) {

			case 1:
				System.out.println("Add a new scale type value");
				String scale = sc.nextLine();
				finalScale += scale + "/";
				addSeve(finalScale);

				break;
			case 2:
				System.out.println("Added all the levels");
				finalScale = finalScale.substring(0, finalScale.length() - 1);
				System.out.println(finalScale);
				scaleType.setScaleType(finalScale);
				new ScaleTypeService().addScale(scaleType);
				Staffmenu();
				break;
			case 3:
				Staffmenu();
				break;

		}
	}

	private static void checkedIn() {
		Connection connection = MakeConnection.makeJDBCConnection();
		List<String> lnames =
				new PatientVisitService().getCheckedInPatients(GlobalConstants.globalLastName);
		if(lnames.size() == 0)
		{
			System.out.println("No patients are currently self-checked in");
		}
		else {
		Map<Integer, String> patients = new HashMap<Integer, String>();
		int count = 1;
		for( String lname : lnames ) {

			patients.put(count, lname);
			System.out.println(count + ". " + lname);
			count++;
		}


		System.out.println("Choose a patient");
		int patient = sc.nextInt();
		String patientName = patients.get(patient);
		System.out.println("1) Enter Vitals 2) Treat Patient 3)Go back ");
		System.out.println("Select a choice");

		int choice = sc.nextInt();

		switch( choice ) {

			case 1:
				Vitals(patientName);
				break;
			case 2:
				TreatPatient(patientName);
				break;
			case 3:
				Staffmenu();
				break;
		}
		}
	}

	public static void TreatPatient(String lname) {

		boolean isAllowed = new StaffService().isAllowed(lname);

		// do logic to move patient to treated list
		StaffService staffService = new StaffService();
		PatientVisitService patientVisitService = new PatientVisitService();
		if(staffService.isAllowed(lname)){
			Date date = new Date(Calendar.getInstance().getTime().getTime());
			patientVisitService.updateCheckOut(date, lname, 2);
		}else {
			System.out.println("you do not have adequate privileges to treat this patient ");

		}
		Staffmenu();

	}

	public static void Vitals(String lname) {
		System.out.println("Enter Temperature: ");
		int temp = sc.nextInt();
		System.out.println("Enter Systolic Blood Pressure");
		int sysBp = sc.nextInt();
		System.out.println("Enter Diastolic Blood Pressure");
		int diaBp = sc.nextInt();
		System.out.println("1) Record 2)Go back ");
		System.out.println("Select a choice");
		Vitals vitals = new Vitals();
		vitals.setDiastolic(diaBp);
		vitals.setSystolic(sysBp);
		vitals.setTemperature(temp);
		int choice = sc.nextInt();

		switch( choice ) {

			case 1: // call vitals enter function with the patient_name
				int vitalId = new VitalService().createVitals(vitals, lname);
				PatientVisitI patientVisit = new PatientVisitService();
				patientVisit.updateVitals(lname, vitalId);
				Calendar calendar = Calendar.getInstance();
				Date date = new Date(new java.util.Date().getTime());
				patientVisit.updateCheckOut(date, lname, 1);
				new PriorityService().setPriority((int) GlobalConstants.visitId);
				Staffmenu();
				break;
			case 2:
				checkedIn();
				break;


		}
	}


	private static void patientRouting() {
		System.out.println("1) Check-In 2) Check-out Knowledgement 3) Go " +
		                   "back");
		int ch = sc.nextInt();
		switch( ch ) {

			case 1:
				CheckIn();
				break;
			case 2:
				PatientAcknowledgement();
				break;
			case 3:
				SignIn_disp();
				break;
		}
	}


	private static void CheckIn() {

		Map<Integer, String> symptomCountMap = displayAllSymptoms();
		sc.nextLine();
		String str = sc.nextLine();
		List<SymptomMetaData> metaDataList = new ArrayList<SymptomMetaData>();
		while( ! ("q").equals(str) ) {
			SymptomMetaData symptomMetaData = new SymptomMetaData();

			String bodyPart = "";
			try {
				int choice = Integer.parseInt(str);
				String symptomId = symptomCountMap.get(choice);
				symptomMetaData.setSymId(symptomId);
				String resp = sc.nextLine();

				while( ! ("d").equals(resp) ) {
					bodyPart = displaySymptomMetadata(symptomId);
					resp = sc.nextLine();
					int key = Integer.parseInt(resp);
					displayMenu(symptomMetaData, key, symptomId);
				}


			}
			catch( Exception e ) {
				System.out.println("Enter number without any spaces");
			}
			if( ! ("").equals(bodyPart) )
				symptomMetaData.setBodyPart(bodyPart);
			metaDataList.add(symptomMetaData);
			displayAllSymptoms();
			str = sc.nextLine();
		}
		int visitId =
				new PatientVisitService().admitPatient(setPatientVisit());
		new SymptomMetaDataService().addSymptomMetaData(metaDataList, visitId);


		System.out.println("Now, you are checked in please wait for a staff to treat you.");
		home();

	}

	private static void displayAllFacilities()
	{
		List<MedicalFacility> medicalFacilities =
				new MedicalFacilityService().getAllFacilities();
		System.out.println("Id\t\t\tName");
		for( MedicalFacility medicalFacility : medicalFacilities ) {
			System.out.println(medicalFacility.getFacility_id() + "\t\t" + medicalFacility.getName());
		}
	}

	private static Map<Integer, String> displayAllSymptoms()
	{
		// CHeck if the patient has already checked in or not before
		// displaying the list
		List<Symptom> symptoms = new SymptomService().getAllSymptoms();
		System.out.println("Id\t\t\tSymptom Name");
		int count = 1;
		Map<Integer, String> symptomCountMap = new HashMap<Integer, String>();
		for( Symptom symptom : symptoms ) {
			System.out.println(count + "\t\t\t" + symptom.getSymptomName());
			symptomCountMap.put(count, symptom.getSymptomId());
			count++;
		}
		System.out.println("press \"q\" when done.");
		System.out.println("press \"n\" to enter new symptom.");
		return symptomCountMap;
	}

	private static String displaySymptomMetadata(String symptomId) {
		String response = new SymptomMetaDataService().isAssociated(symptomId);
		setSymptomMetaDataMap();
		if( response.equals("") ) {
			System.out.println("1\t\tBody Part");
		}


		System.out.println("2\t\tDuration");
		System.out.println("3\t\tRecurring");
		System.out.println("4\t\tSeverity");
		System.out.println("5\t\tCause");
		System.out.println("press \"d\" when done");
		return response;

	}

	private static void displayMenu(SymptomMetaData symptomMetaData, int key,
	                                String symptomId) {

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
				String scale =
						new SeverityScaleService().getSeverityScale(symptomId);
				System.out.println("Enter scale in " + scale);
				resp = sc.nextLine();
				symptomMetaData.setSeverity(resp);
				break;
			case 5:
				resp = sc.nextLine();
				symptomMetaData.setCause(resp);
				break;
		}
	}

	private static void setSymptomMetaDataMap() {
		symptomMetaDataMap.put(1, "Body Part");
		symptomMetaDataMap.put(2, "Duration");
		symptomMetaDataMap.put(3, "isRecurring");
		symptomMetaDataMap.put(4, "Severity");
		symptomMetaDataMap.put(5, "Cause");
	}

	private static PatientVisit setPatientVisit() {
		PatientVisit patientVisit = new PatientVisit();
		patientVisit.setPid(GlobalConstants.globalPid);
		patientVisit.setCheckIn(new Date(Calendar.getInstance().getTime().getTime()));
		patientVisit.setPhase(0);
		patientVisit.setFacilityId(GlobalConstants.globalFacilityId);
		return patientVisit;
	}

	public static void StaffPatientReport() {


		System.out.println("1) Discharge Status 2) Referral Status 3) Treatment 4) Negative Experience 5) Go back 6) Submit ");
		System.out.println("Select a choice");

		int choice = sc.nextInt();


		switch( choice ) {

			case 1:
				DischargeStatus();
				break;
			case 2:
				ReferralStatus();
				break;
			case 3:
				sc.nextLine();
				System.out.println("Enter treatment Description: ");
				treatment_given = sc.nextLine();
				StaffPatientReport();
				break;
			case 4:
				NegativeExperience();
				break;
			case 5: Staffmenu();
				break;
			case 6:
				StaffPatientReportConfirmation();
				break;
		}

	}

	public static void DischargeStatus() {

		System.out.println("1) Successful treatment 2) Deceased 3) Referred 4) Go back ");
		System.out.println("Select a choice");

		int choice = sc.nextInt();

		switch( choice ) {

			case 1:
				discharge_status = "Successful";
				StaffPatientReport();
				break;
			case 2:
				discharge_status = "Deceased";
				StaffPatientReport();
				break;
			case 3:
				discharge_status = "Referred";
				StaffPatientReport();
				break;
			case 4:
				StaffPatientReport();
				break;
		}


	}

	public static void ReferralStatus() {

		System.out.println("1) Facility Id 2) Referred ID 3) Add Reason 4) Go back");
		System.out.println("Select a choice");

		int choice = sc.nextInt();

		switch( choice ) {

			case 1:
				System.out.println("Enter Facility Id");
				referral_facility_id = sc.nextInt();
				ReferralStatus();
				break;
			case 2:
				System.out.println("Enter Referrer Id");
				referrer_id = sc.nextInt();
				ReferralStatus();
				break;
			case 3:
				ReferralReason();
				break;
			case 4:
				StaffPatientReport();
				break;
		}


	}

	public static void ReferralReason() {

		// Display the reason code , name of service and description
		sc.nextLine();
		System.out.println("Referral Reason Codes");
		System.out.println("1) Service unavailable at the time of visit 2) Service not present at facility 3) Non-Payment ");

		System.out.println("1) Record 2) Go back");
		System.out.println("Select a choice");


		int choice = sc.nextInt();

		switch( choice ) {

			case 1:
				System.out.println("Enter Reason code:");
				referral_reason_code = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter the service");
				referral_service_name = sc.nextLine();
				ReferralStatus();
				break;
			case 2:
				ReferralStatus();
				break;
		}

	}

	public static void NegativeExperience() {


		System.out.println("Negative Experience ");
		System.out.println("1) Misdiagnosis 2) Patient Acquired Infection during Hospital Stay");

		System.out.println("1) Negative Experience code 2) Go back");
		System.out.println("Select a choice");

		int choice = sc.nextInt();

		switch( choice ) {

			case 1:
				System.out.println("Enter the Negative Experience code");
				negative_exp_code = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter the Description for Negative " +
				                   "Experience");
				negative_exp_reason = sc.nextLine();
				StaffPatientReport();
				break;
			case 2:
				StaffPatientReport();
				break;
		}

	}

	public static void StaffPatientReportConfirmation() {
		System.out.println("   ***** REPORT *****        ");
		System.out.println("Discharge Status: " + discharge_status);
		System.out.println("Treatment Given: " + treatment_given);
		System.out.println("Referral Facility Id: " + referral_facility_id);
		System.out.println("Referrer Id: " + referrer_id);
		System.out.println("Referral Reason code: " + referral_reason_code);
		System.out.println("Referral Service Name: " + referral_service_name);
		System.out.println("Negative Experience Code: " + negative_exp_code);
		System.out.println("Negative Experience Reason: " + negative_exp_reason);

		System.out.println("***************************************");
		System.out.println("1) Confirm 2) Go back");
		System.out.println("Select a choice");

		int choice = sc.nextInt();

		switch( choice ) {

			case 1: // Push the report in respective tables
				Experience experience = new Experience();
				experience.setDischargeStatus(discharge_status);
				experience.setTreatmentGiven(treatment_given);
				experience.setNegativeExperience(negative_exp_code);
				experience.setNegativeExperienceDescription(negative_exp_reason);
				int expId = new ExperienceService().insertExperience(experience);
				new PatientVisitService().updateExperience(expId);
				if(discharge_status.equals("Referred"))
				{
					ReferralService referralService = new ReferralService();
					int referralId = referralService.insertReferral(referral_facility_id, referrer_id);
					System.out.println(referralId);
					referralService.insertExperienceReferral(expId, referralId);
					System.out.println(referral_reason_code);
					referralService.insertReferralReason(referralId, referral_reason_code, referral_service_name);
				}
				Date date = new Date(Calendar.getInstance().getTime().getTime());
				new PatientVisitService().updateCheckOut(date, "",3);
				Staffmenu();
				break;
			case 2:
				StaffPatientReport();
				break;
		}
	}

	public static void PatientCheckout() {

		System.out.println("      ***** REPORT *****        ");
		System.out.println("Discharge Status: " + discharge_status);
		System.out.println("Treatment Given: " + treatment_given);
		System.out.println("Referral Facility Id: " + referral_facility_id);
		System.out.println("Referrer Id: " + referrer_id);
		System.out.println("Referral Reason code: " + referral_reason_code);
		System.out.println("Referral Service Name: " + referral_service_name);
		System.out.println("Negative Experience Code: " + negative_exp_code);
		System.out.println("Negative Experience Reason: " + negative_exp_reason);

		System.out.println("***************************************");


		System.out.println("1) Yes 2) No 3) Go back");
		System.out.println("Select a choice");

		int choice = sc.nextInt();
		String reasonAck = "";
		switch( choice ) {

			case 1:
				patientRouting();
				break;
			case 2:
				System.out.println("Enter reason for no acknowledgement");
				reasonAck = sc.nextLine();
				// ENter in table Experience
				break;
			case 3:
				patientRouting();
				break;
		}
	}


}