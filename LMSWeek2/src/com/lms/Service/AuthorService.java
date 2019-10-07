package com.lms.Service;

import java.sql.Connection;
import java.util.List;

import com.lms.DAO.AuthorDAO;
import com.lms.POJO.Author;

public class AuthorService{
	AuthorDAO authorDAO = new AuthorDAO();
	
	public void createAuthor(Connection con, int authorId, String authorName) {
		authorDAO.writeInsertAuthor(con, authorId, authorName);
		
		System.out.println("\nAuthor created successfully.");
	}
	
	public void updateAuthor(Connection con, int authorId, String newData, String fieldName) {
		authorDAO.writeUpdateAuthor(con, authorId, newData, fieldName);
	}
	
	public void deleteAuthor(Connection con, int authorId) {
		authorDAO.writeDeleteAuthor(con, authorId);
		
		System.out.println("\nAuthor deleted successfully.");
	}
	
	public void viewAuthor(Connection con) {
		authorDAO.readViewAuthor(con);
	}
	
	public boolean ifExists(Connection con, int authId, boolean checkId) {
		List<Author> list = authorDAO.readAuthor(con);
		
		for(Author i : list) {
			if(i.getAuthorId() == authId) {
				return checkId = false;
			}
		}
		return checkId = true;
	}
	
	public boolean ifNotExists(Connection con, int authId, boolean checkId) {
		List<Author> list = authorDAO.readAuthor(con);
		
		for(Author i : list) {
			if(i.getAuthorId() == authId) {
				return checkId = true;
			}
		}
		return checkId = false;
	}
}
