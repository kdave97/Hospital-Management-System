package com.models;


import java.sql.Date;

/**
 * @author neelparikh
 * @Date 2019-11-11
 */


public class Patient {

	private String fname;
	private String lname;
	private String phone;
	private Date date;

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
