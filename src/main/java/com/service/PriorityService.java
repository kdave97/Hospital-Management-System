package com.service;

import com.connection.MakeConnection;
import com.models.SymptomMetaData;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author neelparikh
 * @Date 2019-11-15
 */


public class PriorityService {
	private Connection connection = MakeConnection.makeJDBCConnection();
	public int addPriority(String priority)
	{
		String sql = "Insert into priority(priority_level) values(?)";
		int key = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, priority);
			int x = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next())
				key = rs.getInt(1);
			connection.close();
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
		return key;
	}

	private List<SymptomMetaData> getSymptomsForVisit(int visitId){
		String sql = "Select sym_id, severity, bodypart from patients_symptom_visit where visit_id = ?";
		List<SymptomMetaData> symptomMetaDataList = new ArrayList<SymptomMetaData>();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, visitId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				SymptomMetaData symptomMetaData = new SymptomMetaData();
				symptomMetaData.setSymId(rs.getString(1));
				symptomMetaData.setBodyPart(rs.getString(3));
				symptomMetaData.setSeverity(rs.getString(2));
				symptomMetaDataList.add(symptomMetaData);
			}
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
		return symptomMetaDataList;
	}

	private List<Integer>  getDistinctRuleIds(){
		List<Integer> ruleList = new ArrayList<Integer>();
		String sql = "Select rule_id from Rules";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ruleList.add(rs.getInt(1));
			}
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
		return ruleList;
	}
	public void setPriority(int visitId){
		List<SymptomMetaData> symptomMetaDataList = getSymptomsForVisit(visitId);
		List<Integer> ruleIds = getDistinctRuleIds();
		System.out.println(ruleIds);
		String sql = "select b_code, sym_id, symbol, cmp_severity from body_symptom_rule where rule_id = ?";
		String priority = "Normal";
		try{

			PreparedStatement ps = connection.prepareStatement(sql);
			for(int i =0; i<ruleIds.size(); i++){
				ps.setInt(1,ruleIds.get(i));
				ResultSet resultSet = ps.executeQuery();
				System.out.println("*****************");
				while( resultSet.next() ){
/*					System.out.println(ruleIds.get(i));
					System.out.print(resultSet.getString(1));
					System.out.print(resultSet.getString(2));
					System.out.print(resultSet.getString(3));
					System.out.print(resultSet.getString(4));*/
					for(int j = 0; j < symptomMetaDataList.size(); j++){
						SymptomMetaData symptomMetaData = symptomMetaDataList.get(0);
						int count = 0;
						if(symptomMetaData.getBodyPart().equals(resultSet.getString(1)))
						{
							count++;
						}
						else
						{
							break;
						}
						if(symptomMetaData.getSymId().equals(resultSet.getString(2))){
							count++;
						}
						else
						{
							break;
						}
						String severity = symptomMetaData.getSeverity();
						try{
							int x = Integer.parseInt(severity);
						}
						catch( Exception e ){

						}
						String symbol = resultSet.getString(3);
						String sql1 = "Select value from scale_type where scale_id = (select scale_id from has_scale where sym_id = ?";
						PreparedStatement statement = connection.prepareStatement(sql1);
						ResultSet rss = statement.executeQuery();
						String level = rss.getString(1);
						String[] levels = level.split("/");
						List<String> levelsList = Arrays.asList(levels);
						int length = levels.length;

						if("<".equals(symbol)){
							for(int k = 0; k<length; k++ ){
								if(severity.equals(levels[k])){

								}
							}
						}


					}
				}
			}

		}
		catch( SQLException e ){
			e.printStackTrace();
		}
	}
}
