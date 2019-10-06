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
	
	public void updatePub(Connection con, int pubId, String newData, String fieldName) {
		PreparedStatement ps = null;
		
		try {
			String sql = "UPDATE tbl_publisher "
							+ "SET " + fieldName + " = ? "
							+ "WHERE publisherId = ?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, newData);
			ps.setInt(2, pubId);
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
	
	public void deletePub(Connection con, int pubId) {
		PreparedStatement ps = null;
		
		try {
			String sql = "DELETE FROM tbl_book "
							+ "WHERE pubId = ?";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, pubId);
			ps.executeUpdate();
			ps.close();
			
			sql = "DELETE FROM tbl_publisher "
					+ "WHERE publisherId = ?";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, pubId);
			ps.executeUpdate();
			
		 	System.out.println("\nPublisher deleted successfully.");
		 	
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
	
	public void viewPub(Connection con) {
		pubDAO.readPub(con);
	}
}
