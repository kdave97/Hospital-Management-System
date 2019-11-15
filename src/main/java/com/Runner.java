package com;

import com.connection.MakeConnection;
import com.models.Address;
import com.models.MedicalFacility;
import com.models.Patient;
import com.models.PatientVisit;
import com.models.Symptom;
import com.models.SymptomMetaData;
import com.service.*;
import com.util.Utility;
import com.util.Validation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		home();

		SignUp();

		

	}
	
	
	public static void home(){

	    System.out.println("Welcome");
	    System.out.println("1) Sign In  2) Sign UP 3)Demo Queries 4)Exit ");
	    System.out.println("Select a choice");

	    int choice=sc.nextInt();

	    switch(choice){

	        case 1: SignIn_disp();
	                break;
	        case 2: SignUp();
	                break;
//	        case 3: DBMSQue();
//	                break;
	        case 4: home();
	                break;
	    }
	    
		}
		
		public static void SignIn_disp(){
	        System.out.println("Welcome to Sign In");
	        System.out.println("1) Sign In 2) Go back");
	        int ch=sc.nextInt();
	        switch(ch){

	        case 1: signIn();
	                break;
	        case 2: home();
	                break;
	    }
	    }
	
	
	
	
	private static void SignUp(){
		sc.nextLine();
        System.out.println("Enter first name: ");
        String su_fname=sc.nextLine();
        System.out.println("Enter last name: ");
        String su_lname=sc.nextLine();
		System.out.println("Enter Date of birth in (\"mm-dd-yyyy\") format");
		String date = sc.nextLine();
		while( ! Validation.validateDate(date) ) {
			System.out.println("Invalid Date format.. Please Enter again");
			date = sc.nextLine();
		}
		
        System.out.println("Enter Apartment number: ");
        int su_apt_num=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter street name: ");
        String su_street=sc.nextLine();
        System.out.println("Enter city: ");
        String su_city=sc.nextLine();
        System.out.println("Enter state: ");
        String su_state=sc.nextLine();
        System.out.println("Enter country: ");
        String su_country=sc.nextLine();
        System.out.println("Enter Phone Number: ");
        String su_phone=sc.nextLine();


        System.out.println("Press 1 to Sign Up or Press 2 to return to Home Menu");
        int ch=sc.nextInt();
        switch(ch){

        case 1: //Push data to database
    		Patient patient = new Patient();
    		patient.setFname(su_fname);
    		patient.setLname(su_lname);
    		Calendar calendar = Calendar.getInstance();
    		int[] dob = Utility.parseDate(date, "-");
    		calendar.set(dob[2], dob[0]-1, dob[1]);
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
        case 2: home();
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
			patient_routing();
		}
		else
		{
			StaffI staffI = new StaffService();
			boolean response = staffI.getStaff(lname, sqlDate);
			if(!response)
			{
				System.out.println("Invalid Credentials.. Please try again");
				signIn();
			}
			GlobalConstants.globalLastName = lname;
			GlobalConstants.globalRole = "staff";
			GlobalConstants.globalFacilityId = facility_id;
			Staffmenu();
		}
	}
	
	public static void Staffmenu(){

	    System.out.println("Welcome Staff!");
	    System.out.println("1) Checked-In patient list 2) Treated Patient List 3)Add Symptoms 4) Add Severity Scale 5) Add Assesment Rule 6)Go back ");
	    System.out.println("Select a choice");

	    int choice=sc.nextInt();

	    switch(choice){

	        case 1: checkedIn();
	                break;
	        case 2: //Treated_Patient();
	                break;
	        case 3: Add_Symp();
	                break;
	        case 4: Add_Seve();
	                break;
	        case 5: Add_Rule();
	        break;
	        case 6: SignIn();
	        break;
	    }
	}
	
	public static void Add_Symp(){
		System.out.println("Enter Symptom id: ");
		String sym_id = sc.nextLine();
		System.out.println("Enter Symptom Name: ");
		String sym_name=sc.nextLine();
		System.out.println("Enter Body Part");
		String body_part=sc.nextLine();
		//display the type of severity to choose from
		System.out.println("Enter Severity");
		String severity=sc.nextLine();
		System.out.println("1) Record 2)Go back ");
		System.out.println("Select a choice");

		int choice=sc.nextInt();

		switch(choice){

		    case 1: //recordSystem();
		    break;
		    case 2: Staffmenu();
		            break;
		    }
		}

	public static void Add_Seve() {
		System.out.println("1) Add level for this scale 2) No more levels 3)Go back ");
		System.out.println("Select a choice");

		int choice=sc.nextInt();

		switch(choice){

		    case 1:
		    break;
		    case 2: System.out.println("Added all the levels");
		    Staffmenu();
		            break;
		    case 3: Staffmenu();
		    break;

		    }
		}
	
	 private static void checkedIn(){
		 Connection connection = MakeConnection.makeJDBCConnection();
		 Map<Integer, String> patients = new HashMap<Integer, String>(); 
		 String sql = "select lname from PatientVisit where phase = 0 and facility_id in (select facility_id from staff where name = ?)";
		 PreparedStatement ps = null;
			String response = "";
			try {
				ps = connection.prepareStatement(sql);
				ps.setLong(1,GlobalConstants.globalFacilityId);
				ResultSet resultSet = ps.executeQuery();
				int count = 1;
				while( resultSet.next() )
				{
					response = resultSet.getString(1);
					patients.put(count, response);
					System.out.println(count+". "+response);
					count++;
				}
			}
			catch( SQLException e ) {
				e.printStackTrace();
				System.out.println("There was an error processing query");
			};
			
			System.out.println("Choose a patient");
			int patient = sc.nextInt();
			String patient_name  = patients.get(patient);
		    System.out.println("1) Enter Vitals 2) Treat Patient 3)Go back ");
		    System.out.println("Select a choice");

		    int choice=sc.nextInt();

		switch(choice){

		    case 1: Vitals();
		    		break;
		    case 2: TreatPatient(patient_name);
		            break;
		    case 3: Staffmenu();
		            break;
		}
		}
	 
	 public static void TreatPatient(String lname) {
		 Connection connection = MakeConnection.makeJDBCConnection();
		
		 String sql = " ";
		 PreparedStatement ps = null;
			int response ;
			try {
				ps = connection.prepareStatement(sql);
				//ps.set();
				ResultSet resultSet = ps.executeQuery();
				
			try {
				if(resultSet.next())
				{
					response = resultSet.getInt(1);
					if (response >= 1) {
						String sql1 = "update table PatientVisit (phase) values (2) where pid = (Select pid from patitents where lname = ?) and phase = 1 and facility_id = ?" ;
						ps = connection.prepareStatement(sql1);
						ps.setString(1,lname);
						ps.setLong(2, GlobalConstants.globalFacilityId);
						ps.executeUpdate();
					}
					
				}
			}catch( SQLException e ) {
				e.printStackTrace();
				System.out.println("There was an error processing query");
			}
			}
		
			catch( SQLException e ) {
				e.printStackTrace();
				System.out.println("There was an error processing query");
			}
			
		 
		 
	 }
	 public static void Vitals(){
		 System.out.println("Enter Temperature: ");
		 int temp=sc.nextInt();
		 System.out.println("Enter Systolic Blood Pressure");
		 int sysBp=sc.nextInt();
		 System.out.println("Enter Diastolic Blood Pressure");
		 int diaBp=sc.nextInt();
		 System.out.println("1) Record 2)Go back ");
		 System.out.println("Select a choice");

		 int choice=sc.nextInt();

		 switch(choice){

		     case 1: // call vitals enter function with the patient_name
		     break;
		     case 2: checkedIn();
		             break;


		     }
		 }
	 
	 
	private static void patient_routing() {
		System.out.println("1) Check-In 2) Check-out Knowledgement 3) Go back");
		int ch=sc.nextInt();
		        switch(ch){

		        case 1: CheckIn();
		                break;
		        case 2: CheckOutAck();
		                break;
		        case 3: SignIn_disp();
		        break;
		    }
		}
	
	private static void CheckOutAck() {
		
		
		
		
	}
	
	private static void CheckIn() {
		
		Map<Integer, String> symptomCountMap = displayAllSymptoms();
		  
		  String str = sc.nextLine();
		  while(!("q").equals(str)) { 
		  SymptomMetaData symptomMetaData = new SymptomMetaData();
		  String bodyPart = "";
		  try { 
			  int choice = Integer.parseInt(str);
			  String symptomId = symptomCountMap.get(choice);
			  String resp = sc.nextLine();
		  
		  while(!("d").equals(resp))
		  { bodyPart = displaySymptomMetadata(symptomId);
		  resp = sc.nextLine();
		  int key = Integer.parseInt(resp);
		  displayMenu(symptomMetaData,key); }

		  } catch( Exception e ) {
		  System.out.println("Enter number without any spaces"); }
		  if(!("").equals(bodyPart)) symptomMetaData.setBodyPart(bodyPart);
		  displayAllSymptoms();
		  str = sc.nextLine(); }
		  new PatientVisitService().admitPatient(setPatientVisit());

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