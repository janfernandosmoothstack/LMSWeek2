package com.lms.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class LibBranchDAO {
	public void writeLibBran(Connection con, int branchId, String branchName, String branchAddress) {
		PreparedStatement ps = null;
		
		try {
			String sql = "INSERT INTO tbl_library_branch "
							+ "VALUES (?, ?, ?)";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, branchId);
			ps.setString(2, branchName);
			ps.setString(3, branchAddress);
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
	
	public void readLibBran(Connection con) {
		try {
			String sql = "SELECT * FROM tbl_library_branch";
	        
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int columnsNumber = rsmd.getColumnCount();
	        
	        System.out.println("ID\t\tName\t\t\tAddress");
	    
	        while (rs.next()) {
	            //Print one row
	            for(int i = 1 ; i <= columnsNumber; i++) {
	            	System.out.print(rs.getString(i) + "\t\t"); //Print one element of a row
	            }
	              	
	            System.out.println();//Move to the next line to print the next row.
	    	}
	        
		} catch (SQLException e) {
			System.out.print(e);
		}
	}
}
