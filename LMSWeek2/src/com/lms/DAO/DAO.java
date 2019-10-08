package com.lms.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {	
	public void delete(Connection con, int id, String idFieldName, String tblName) {
		PreparedStatement ps = null;
		
		try {
			String sql = "DELETE FROM " + tblName
							+ " WHERE " + idFieldName + " = ?";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}
	
	//Used to get data from table that the user does not want updated
	public String getStringData(Connection con, int id, String idFieldName, String fieldName, String tblName) {
		PreparedStatement ps = null;
		String data = "";
		
		try {
			String sql = "SELECT " + fieldName + " FROM " + tblName 
					+ " WHERE " + idFieldName + " = ?";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
	        
	        while(rs.next()) {
	        	data = rs.getString(fieldName);
	        }
	        
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		
		return data;
	}
	
	//Used to get data from table that the user does not want updated
	public int getIntData(Connection con, int id, String idFieldName, String fieldName, String tblName) {
		PreparedStatement ps = null;
		int data = 0;
		
		try {
			String sql = "SELECT " + fieldName + " FROM " + tblName 
					+ " WHERE " + idFieldName + " = ?";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
	        
	        while(rs.next()) {
	        	data = rs.getInt(fieldName);
	        }
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		
		return data;
	}
}
