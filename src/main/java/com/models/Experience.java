package com.models;

/**
 * @author neelparikh
 * @Date 2019-11-16
 */


public class Experience {
	private int expId;
	private String dischargeStatus;
	private String treatmentGiven;
	private int negativeExperience;
	private String negativeExperienceDescription;
	private String ackStatus;
	private String ackReason;

	public int getExpId() {
		return expId;
	}

	public void setExpId(int expId) {
		this.expId = expId;
	}

	public String getDischargeStatus() {
		return dischargeStatus;
	}

	public void setDischargeStatus(String dischargeStatus) {
		this.dischargeStatus = dischargeStatus;
	}

	public String getTreatmentGiven() {
		return treatmentGiven;
	}

	public void setTreatmentGiven(String treatmentGiven) {
		this.treatmentGiven = treatmentGiven;
	}

	public int getNegativeExperience() {
		return negativeExperience;
	}

	public void setNegativeExperience(int negativeExperience) {
		this.negativeExperience = negativeExperience;
	}

	public String getNegativeExperienceDescription() {
		return negativeExperienceDescription;
	}

	public void setNegativeExperienceDescription(String negativeExperienceDescription) {
		this.negativeExperienceDescription = negativeExperienceDescription;
	}

	public String getAckStatus() {
		return ackStatus;
	}

	public void setAckStatus(String ackStatus) {
		this.ackStatus = ackStatus;
	}

	public String getAckReason() {
		return ackReason;
	}

	public void setAckReason(String ackReason) {
		this.ackReason = ackReason;
	}
}
