package com.lms.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.lms.DAO.AuthorDAO;

public class AuthorService {
	AuthorDAO authorDAO = new AuthorDAO();
	
	public void createAuthor(Connection con, int authorId, String authorName) {
		authorDAO.writeAuthor(con, authorId, authorName);
		
		System.out.println("\nAuthor created successfully.");
	}
	
	public void updateAuthor(Connection con, int authorId, String authorName) {
		PreparedStatement ps = null;
		
		try {
			String sql = "UPDATE tbl_author "
							+ "SET authorName = ? "
							+ "WHERE authorId = ?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, authorName);
			ps.setInt(2, authorId);
			ps.executeUpdate();
			
		 	System.out.println("\nAuthor updated successfully.");
		 	
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
	
	public void deleteAuthor(Connection con, int authorId) {
		PreparedStatement ps = null;
		
		try {
			String sql = "DELETE FROM tbl_book "
							+ "WHERE authId = ?";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, authorId);
			ps.executeUpdate();
			ps.close();
			
			sql = "DELETE FROM tbl_author "
					+ "WHERE authorId = ?";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, authorId);
			ps.executeUpdate();
			
		 	System.out.println("\nAuthor deleted successfully.");
		 	
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
	
	public void viewAuthor(Connection con) {
		authorDAO.readAuthor(con);
	}
}
