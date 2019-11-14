package com.models;

import java.sql.Date;

/**
 * @author neelparikh
 * @Date 2019-11-13
 */


public class PatientVisit {

	private int visitId;
	private Date treatmentStart;
	private Date checkIn;
	private Date checkOut;
	private int phase;
	private int expId;
	private long facilityId;
	private int pid;
	private String priority;

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public int getVisitId() {
		return visitId;
	}

	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}

	public Date getTreatmentStart() {
		return treatmentStart;
	}

	public void setTreatmentStart(Date treatmentStart) {
		this.treatmentStart = treatmentStart;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public int getExpId() {
		return expId;
	}

	public void setExpId(int expId) {
		this.expId = expId;
	}

	public long getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(long facilityId) {
		this.facilityId = facilityId;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
}
