package com.service;

import com.connection.MakeConnection;
import com.models.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author neelparikh
 * @Date 2019-11-11
 */


public class AddressService implements AddressI {
	private Connection connection = MakeConnection.makeJDBCConnection();
	public void insertAddress(Address address, int pid) {
		String sql = "Insert into Address(house_number, street, city, state, country, pid) values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1,address.getNumber());
			ps.setString(2, address.getStreet());
			ps.setString(3, address.getCity());
			ps.setString(4, address.getState());
			ps.setString(5, address.getCountry());
			ps.setInt(6, pid);
			int rows_updated = ps.executeUpdate();
			if(rows_updated == 0)
			{
				System.out.println("There is an error while inserting row");
			}
			connection.close();


		}
		catch( SQLException e ) {
			System.out.println("There was an error inserting address");
			e.printStackTrace();
		}



	}
}
