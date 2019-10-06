package com.lms.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.lms.DAO.BookDAO;

public class BookService {
	BookDAO bookDAO = new BookDAO();
	
	public void createBook(Connection con, int bookId, String title, int authId, int pubId) {
		bookDAO.writeBook(con, bookId, title, authId, pubId);
		
		System.out.println("\nBook created successfully.");
	}
	
	public void updateBook(Connection con, int bookId, String title, int authId, int pubId) {
		PreparedStatement ps = null;
		
		try {
			String sql = "UPDATE tbl_book "
							+ "SET title = ?, authId = ?, pubId = ? "
							+ "WHERE bookId = ?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, title);
			ps.setInt(2, authId);
			ps.setInt(3, pubId);
			ps.setInt(4, bookId);
			ps.executeUpdate();
			
		 	System.out.println("\nBook updated successfully.");
		 	
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
	
	public void deleteBook() {
		
	}
	
	public void viewBook(Connection con) {
		bookDAO.readBook(con);
	}
}
