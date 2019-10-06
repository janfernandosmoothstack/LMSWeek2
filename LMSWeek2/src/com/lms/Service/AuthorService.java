package com.lms.Service;

import java.sql.Connection;

import com.lms.DAO.AuthorDAO;

public class AuthorService extends Service{
	AuthorDAO authorDAO = new AuthorDAO();
	
	public void createAuthor(Connection con, int authorId, String authorName) {
		authorDAO.writeAuthor(con, authorId, authorName);
		
		System.out.println("\nAuthor created successfully.");
	}
	
	public void updateAuthor(Connection con, int authorId, String newData, String fieldName) {
		super.updateString(con, authorId, newData, fieldName, "authorId", "tbl_author");
	}
	
	public void deleteAuthor(Connection con, int authorId) {
		super.delete(con, authorId, "authId", "tbl_book");
		super.delete(con, authorId, "authorId", "tbl_author");
		
		System.out.println("\nAuthor deleted successfully.");
	}
	
	public void viewAuthor(Connection con) {
		authorDAO.readAuthor(con);
	}
}
