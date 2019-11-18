package com.models;

/**
 * @author neelparikh
 * @Date 2019-11-15
 */


public class BodySymptomRule {
	private String bCode;
	private int ruleId;
	private String symptomId;
	private String symbol;
	private String severity;

	public String getbCode() {
		return bCode;
	}

	public void setbCode(String bCode) {
		this.bCode = bCode;
	}

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public String getSymptomId() {
		return symptomId;
	}

	public void setSymptomId(String symptomId) {
		this.symptomId = symptomId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}
}
