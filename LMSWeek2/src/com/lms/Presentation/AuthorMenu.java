package com.lms.Presentation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lms.Service.AuthorService;

public class AuthorMenu implements MenuInterface {
	AuthorService authService = new AuthorService();
	
	public void showMenu(Connection con) {
		String choice = "";
		
		do {
			System.out.println("Author Menu");
			MenuInterface.crudMenu();
			
			boolean checkChoice = false;
			
			//loop until user enters a valid choice
			while(checkChoice != true) {
				choice = MenuInterface.readString();
				
				switch(choice) {
					case "1": //Create
						toCreate(con);
						checkChoice = true;
						break;
					case "2": //Update
						toUpdate(con);
						checkChoice = true;
						break;
					case "3": //Delete
						toDelete(con);
						checkChoice = true;
						break;
					case "4": //View
						toView(con);
						checkChoice = true;
						break;
					case "5": //Exit
						return;
					default:
						System.out.println("Please enter a valid option.");
						break;
				}
			}
		}while(choice != "5");
	}
	
	public void toCreate(Connection con) {
		boolean checkId  = false;
		int authorId = 0;
		String authorName = "";
		
		//Validate ID
		while(checkId != true) {
			System.out.println("\nPlease enter the author ID:");
			authorId = MenuInterface.readInt();
			
			String sql = "SELECT authorId FROM tbl_author "
							+ "WHERE authorId = ?";
			
			checkId = MenuInterface.ifExists(con, authorId, sql);
		}
		
		System.out.println("Please enter the author's name:");
		authorName = MenuInterface.readString();
		
		authService.createAuthor(con, authorId, authorName);
		MenuInterface.cont();
	}
	
	public void toUpdate(Connection con) {
		boolean checkId  = false;
		int authorId = 0;
		String authorName = "";
		
		//Validate ID
		while(checkId != true) {
			System.out.println("\nPlease enter the author ID:");
			authorId = MenuInterface.readInt();
			
			String sql = "SELECT authorId FROM tbl_author "
							+ "WHERE authorId = ?";
			
			checkId = MenuInterface.ifNotExists(con, authorId, sql);
		}
		
		System.out.println("Please enter the new author's name:");
		authorName = MenuInterface.readString();
		
		authService.updateAuthor(con, authorId, authorName);
		MenuInterface.cont();
	}
	
	public void toDelete(Connection con){
		System.out.println("\nPlease enter the author ID:");
		int authorId = MenuInterface.readInt();
		//validate id
		
		//Call delete method
	}
	
	public void toView(Connection con) {
		authService.viewAuthor(con);
		MenuInterface.cont();
	}
	
	
}
