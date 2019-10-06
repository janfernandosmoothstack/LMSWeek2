package com.lms.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class BorrowerDAO {
	public void writeBorr(Connection con, int cardNo, String name, String address, String phone) {
		PreparedStatement ps = null;
		
		try {
			String sql = "INSERT INTO tbl_borrower "
							+ "VALUES (?, ?, ?, ?)";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, cardNo);
			ps.setString(2, name);
			ps.setString(3, address);
			ps.setString(4, phone);
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
	
	public void readBorr(Connection con) {
		try {
			String sql = "SELECT * FROM tbl_borrower";
	        
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int columnsNumber = rsmd.getColumnCount();
	        
	        System.out.println("ID\t\tName\t\tAddress\t\tPhone");
	    
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
