package com.util;

/**
 * @author neelparikh
 * @Date 2019-11-12
 */


public class Utility {

	public static int[] parseDate(String date, String delimeter){
		String[] str = date.split(delimeter);
		int[] arr = new int[3];
		arr[0] = Integer.parseInt(str[0]);
		arr[1] = Integer.parseInt(str[1]);
		arr[2] = Integer.parseInt(str[2]);
		return arr;
	}

}
