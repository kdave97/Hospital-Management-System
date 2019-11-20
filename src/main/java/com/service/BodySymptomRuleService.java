package com.service;

import com.connection.MakeConnection;
import com.models.BodySymptomRule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author neelparikh
 * @Date 2019-11-15
 */


public class BodySymptomRuleService {
	private Connection connection = MakeConnection.makeJDBCConnection();
	public void insertAssessmentRule(List<BodySymptomRule> bodySymptomRules){
		String sql = "Insert into body_symptom_rule values (?,?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			for(BodySymptomRule bodySymptomRule: bodySymptomRules) {
				ps.setString(1, bodySymptomRule.getbCode());
				ps.setInt(2, bodySymptomRule.getRuleId());
				ps.setString(3, bodySymptomRule.getSymptomId());
				ps.setString(4, bodySymptomRule.getSymbol());
				ps.setString(5, bodySymptomRule.getSeverity());
				int x = ps.executeUpdate();
				if( x == 0 ) {
					System.out.println("unsuccessful");
				} else
					System.out.println("Successful");
			}
			connection.close();

		}
		catch( SQLException e ) {
			e.printStackTrace();
		}

	}
}
