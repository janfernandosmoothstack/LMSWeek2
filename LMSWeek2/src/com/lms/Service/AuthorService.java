package com.lms.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthorService {
	public void createAuthor(Connection con, int authorId, String authorName) {
		PreparedStatement ps = null;
		
		try {
			String sql = "INSERT INTO tbl_author "
							+ "VALUES (?, ?)";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, authorId);
			ps.setString(2, authorName);
			ps.executeUpdate();
			
		 	System.out.println("\nAuthor created successfully!!!");
		 	
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
	
	public void updateAuthor(Connection con, int authorId, String authorName) {
		PreparedStatement ps = null;
		
		try {
			String sql = "UPDATE tbl_author "
							+ "SET authorName = ? "
							+ "WHERE authorId = ?";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, authorId);
			ps.setString(2, authorName);
			ps.executeUpdate();
			
		 	System.out.println("\nAuthor updated successfully!!!");
		 	
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
	
	public void deleteAuthor() {
		
	}
	
	public void viewAuthor(Connection con) {
		try {
			String sql = "SELECT * FROM tbl_author";
	        
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int columnsNumber = rsmd.getColumnCount();
	        
	        System.out.println("ID\tAuthor Name");
	    
	        while (rs.next()) {
	            //Print one row
	            for(int i = 1 ; i <= columnsNumber; i++) {
	            	System.out.print(rs.getString(i) + "\t "); //Print one element of a row
	            }
	              	
	            System.out.println();//Move to the next line to print the next row.
	    	}
	        
		} catch (SQLException e) {
			System.out.print(e);
		}
	}
}
