package com.lms.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class BookDAO {
	public void writeBook(Connection con, int bookId, String title, int authId, int pubId) {
		PreparedStatement ps = null;
		
		try {
			String sql = "INSERT INTO tbl_book "
							+ "VALUES (?, ?, ?, ?)";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, bookId);
			ps.setString(2, title);
			ps.setInt(3, authId);
			ps.setInt(4, pubId);
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
	
	public void readBook(Connection con) {
		try {
			String sql = "SELECT * FROM tbl_book";
	        
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int columnsNumber = rsmd.getColumnCount();
	        
	        System.out.println("ID\t\tTitle\t\tAuthID\t\tPubID");
	    
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
