package com.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author neelparikh
 * @Date 2019-11-12
 */


public class Validation {
	public static boolean validateDate(String date){
		try{
			DateFormat date1 = new SimpleDateFormat("MM-dd-yyyy");
			date1.setLenient(false);
			date1.parse(date);
		}catch( Exception e ){
			return false;
		}
		return true;
	}

}
