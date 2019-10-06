package com.lms.Service;

import java.sql.Connection;

import com.lms.DAO.BookDAO;

public class BookService {
	BookDAO bookDAO = new BookDAO();
	
	public void createBook(Connection con, int bookId, String title, int authId, int pubId) {
		bookDAO.writeInsertBook(con, bookId, title, authId, pubId);
		
		System.out.println("\nBook created successfully.");
	}
	
	public void updateBook(Connection con, int bookId, String newData, String fieldName) {
		bookDAO.writeUpdateBook(con, bookId, newData, fieldName);
	}
	
	public void deleteBook() {
		
	}
	
	public void viewBook(Connection con) {
		bookDAO.readViewBook(con);
	}
}
