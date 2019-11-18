package com.service;

import com.connection.MakeConnection;

import java.sql.*;

/**
 * @author neelparikh
 * @Date 2019-11-15
 */


public class RuleService {
	private Connection connection = MakeConnection.makeJDBCConnection();
	public int insertRule(int priorityId){
		String sql = "insert into Rules(priority_id) values(?)";
		int key = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1,priorityId);
			int x = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			while( rs.next() ){
				key = rs.getInt(1);
			}
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
		return key;
	}

	public void updatePriorityId(int priorityId, int ruleId){
		String sql = "update Rules set priority_id = ? where rule_id = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, priorityId);
			ps.setInt(2, ruleId);
			ps.executeUpdate();

		}
		catch( SQLException e ) {
			e.printStackTrace();
		}

	}
}
