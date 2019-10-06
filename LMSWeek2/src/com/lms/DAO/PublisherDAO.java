package com.lms.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.POJO.Publisher;

public class PublisherDAO extends DAO {
	public void writeInsertPub(Connection con, int pubId, String pubName, String pubAddress, String pubPhone) {
		PreparedStatement ps = null;
		
		try {
			String sql = "INSERT INTO tbl_publisher "
							+ "VALUES (?, ?, ?, ?)";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, pubId);
			ps.setString(2, pubName);
			ps.setString(3, pubAddress);
			ps.setString(4, pubPhone);
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
	
	public void writeUpdatePub(Connection con, int pubId, String newData, String fieldName) {
		super.updateString(con, pubId, newData, fieldName, "publisherId", "tbl_publisher");
	}
	
	public void writeDeletePub(Connection con, int pubId) {
		super.delete(con, pubId, "pubId", "tbl_book");
		super.delete(con, pubId, "publisherId", "tbl_publisher");
	}
	
	public void readViewPub(Connection con) {
		try {
			String sql = "SELECT * FROM tbl_publisher";
	        
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
	
	public List<Publisher> readPub(Connection con) {
		List<Publisher> pubList = null;
		
		try {
			Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT publisherId, publisherName, publisherAddress, publisherPhone FROM tbl_publisher");         

        	pubList = new ArrayList<Publisher>();
        	
            while (rs.next()) {
            	Publisher pub = new Publisher();
            	
            	pub.setPublisherId(rs.getInt("publisherId"));
            	pub.setPublisherName(rs.getString("publisherName"));
            	pub.setPublisherAddress(rs.getString("publisherAddress"));
            	pub.setPublisherPhone(rs.getString("publisherPhone"));
                pubList.add(pub);
            }
        } catch (SQLException e) {
        	System.out.println(e);
        }
		
		return pubList;
	}
}
