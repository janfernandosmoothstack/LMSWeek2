package com.lms.Service;

import java.sql.Connection;
import java.util.List;

import com.lms.DAO.BookDAO;
import com.lms.POJO.Book;

public class BookService {
	BookDAO bookDAO = new BookDAO();
	
	public void createBook(Connection con, int bookId, String title, int authId, int pubId) {
		bookDAO.writeInsertBook(con, bookId, title, authId, pubId);
		
		System.out.println("\nBook created successfully.");
	}
	
	public void updateBook(Connection con, int bookId, String title, int authId, int pubId) {
		bookDAO.writeUpdateBook(con, bookId, title, authId, pubId);
		
		System.out.println("\nBook updated successfully.");
	}
	
	public void deleteBook(Connection con, int bookId) {
		bookDAO.writeDeleteBook(con, bookId);
		
		System.out.println("\nBook deleted successfully.");
	}
	
	public void viewBook(Connection con) {
		bookDAO.readViewBook(con);
	}
	
	public String getDataS(Connection con, int id, String fieldName) {
		return bookDAO.getBookDataS(con, id, fieldName);
	}
	
	public int getDataI(Connection con, int id, String fieldName) {
		return bookDAO.getBookDataI(con, id, fieldName);
	}
	
	public boolean ifExists(Connection con, int bookId, boolean checkId) {
		List<Book> list = bookDAO.readBook(con);
		
		for(Book i : list) {
			if(i.getBookId() == bookId) {
				return checkId = false;
			}
		}
		return checkId = true;
	}
	
	public boolean ifNotExists(Connection con, int bookId, boolean checkId) {
		List<Book> list = bookDAO.readBook(con);
		
		for(Book i : list) {
			if(i.getBookId() == bookId) {
				return checkId = true;
			}
		}
		return checkId = false;
	}
}
