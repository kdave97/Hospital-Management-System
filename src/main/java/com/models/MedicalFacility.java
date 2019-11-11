package com.models;

/**
 * @author neelparikh
 * @Date 2019-11-09
 */


public class MedicalFacility {
	private long facility_id;
	private String name;
	private String classification;
	private String address;
	private int capacity;

	public long getFacility_id() {
		return facility_id;
	}

	public void setFacility_id(long facility_id) {
		this.facility_id = facility_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
