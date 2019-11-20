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
		   String sql = "select b_code, sym_id, symbol, cmp_severity from body_symptom_rule where rule_id = ?";
		   String priority = "Normal";
		   int biggerCount = 0;
		   int macthedRuleId = 0;
		   try{

		      PreparedStatement ps = connection.prepareStatement(sql);
		      for(int i =0; i<ruleIds.size(); i++){
		         ps.setInt(1,ruleIds.get(i));
		         ResultSet resultSet = ps.executeQuery();

		         while( resultSet.next() ){
		            
		            for(int j = 0; j < symptomMetaDataList.size(); j++){
		            	int count = 0;
		            	SymptomMetaData symptomMetaData = symptomMetaDataList.get(j);

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
		               String ruleSeverity = resultSet.getString(4);
		               
		               boolean flag = false;
		               try{
		                  int x = Integer.parseInt(severity);
		                  String symbol = resultSet.getString(3);
		                  int intSeverity = Integer.parseInt(ruleSeverity);
		                  flag = true;
		                  if("<".equals(symbol)){
		                     if(x < intSeverity){
		                        count++;
		                     }
		                  }
		                  else if(">".equals(symbol)){
		                     if(x > intSeverity)
		                     {
		                        count++;
		                     }
		                  }
		                  else
		                  {
		                     if( x == intSeverity)
		                     {
		                        count++;
		                     }
		                  }
		               }
		               catch( Exception e ){
		            	   	flag = false;
		               }

		               if(!flag) {
		                  if( ruleSeverity.equals(severity) ) {
		                     count++;
		                  } else {
		                     break;
		                  }
		               }
		              if(count == 3)
		            	  biggerCount++;
		            }
		            
		            
		         }
		         macthedRuleId = ruleIds.get(i);
		      }
		      
		   }
		   catch( SQLException e ){
		      e.printStackTrace();
		   }
		   if (biggerCount> 0)
		   {
			   String sql2 = "Select priority_id from rules where rule_id = ?";
			   try {
				PreparedStatement ps = connection.prepareStatement(sql2);
				ps.setInt(1, macthedRuleId);
				ResultSet rs = ps.executeQuery();
				int priorityId = 0;
				while(rs.next()) {
					priorityId = rs.getInt(1);
				}
				String sql3 = "Select priority_level from priority where priority_id = ?";
				 try {
						ps = connection.prepareStatement(sql3);
						ps.setInt(1, macthedRuleId);
						rs = ps.executeQuery();
						
						while(rs.next()) {
							priority = rs.getString(1);
						}

				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }catch (Exception e) {
			// TODO: handle exception
		}
		}
		   String sql4 = "update patientvisit set priority = ? where visit_id = ?";
		   
			try {
				PreparedStatement ps = connection.prepareStatement(sql4);
				ps.setString(1, priority);
				ps.setInt(2, visitId);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
}
