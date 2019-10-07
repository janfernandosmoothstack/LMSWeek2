package com.lms.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.POJO.Author;

public class AuthorDAO extends DAO{
	public void writeInsertAuthor(Connection con, int authorId, String authorName) {
		PreparedStatement ps = null;
		
		try {
			String sql = "INSERT INTO tbl_author "
							+ "VALUES (?, ?)";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, authorId);
			ps.setString(2, authorName);
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
	
	public void writeUpdateAuthor(Connection con, int authorId, String newData, String fieldName) {
		super.updateString(con, authorId, newData, fieldName, "authorId", "tbl_author");
	}
	
	public void writeDeleteAuthor(Connection con, int authorId) {
		super.delete(con, authorId, "authId", "tbl_book");
		super.delete(con, authorId, "authorId", "tbl_author");
	}
	
	public void readViewAuthor(Connection con) {
		try {
			String sql = "SELECT * FROM tbl_author";
	        
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int columnsNumber = rsmd.getColumnCount();
	        
	        System.out.println("ID\t\tAuthor Name");
	    
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
	
	public List<Author> readAuthor(Connection con) {
		List<Author> authorList = null;
		
		try {
			Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT authorId, authorName FROM tbl_author");         

        	authorList = new ArrayList<Author>();
        	
            while (rs.next()) {
            	Author auth = new Author();
            	
                auth.setAuthorId(rs.getInt("authorId"));
                auth.setAuthorName(rs.getString("authorName"));
                authorList.add(auth);
            }
        } catch (SQLException e) {
        	System.out.println(e);
        }
		
		return authorList;
	}
}
