package com.models;

/**
 * @author neelparikh
 * @Date 2019-11-14
 */


public class SymptomMetaData {
	private String bodyPart;
	private String duration;
	private String isRecurring;
	private String cause;
	private String severity;

	public String getBodyPart() {
		return bodyPart;
	}

	public void setBodyPart(String bodyPart) {
		this.bodyPart = bodyPart;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getIsRecurring() {
		return isRecurring;
	}

	public void setIsRecurring(String isRecurring) {
		this.isRecurring = isRecurring;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}
}
