package com.lms.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.lms.DAO.PublisherDAO;

public class PublisherService {
	PublisherDAO pubDAO = new PublisherDAO();
	
	public void createPub(Connection con, int pubId, String pubName, String pubAddress, String pubPhone) {
		pubDAO.writePub(con, pubId, pubName, pubAddress, pubPhone);
		
		System.out.println("\nPublisher created successfully.");
	}
	
	public void updatePub(Connection con, int pubId, String pubName, String pubAddress, String pubPhone) {
		PreparedStatement ps = null;
		
		try {
			String sql = "UPDATE tbl_publisher "
							+ "SET publisherName = ?, publisherAddress = ?, publisherPhone = ? "
							+ "WHERE publisherId = ?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, pubName);
			ps.setString(2, pubAddress);
			ps.setString(3, pubPhone);
			ps.setInt(4, pubId);
			ps.executeUpdate();
			
		 	System.out.println("\nPublisher updated successfully.");
		 	
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
	
	public void deletePub() {
		
	}
	
	public void viewPub(Connection con) {
		pubDAO.readPub(con);
	}
}
