package com.lms.Presentation;

import java.sql.Connection;

import com.lms.Service.AuthorService;

public class AuthorMenu implements MenuInterface {
	AuthorService authService = new AuthorService();
	String choice = "";
	
	public void showMenu(Connection con) {
		do {
			MenuInterface.clr();
			
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
			
			checkId = MenuInterface.ifExists(con, authorId, "authorId", "tbl_author");
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
		
		System.out.println();
		authService.viewAuthor(con);
		
		//Validate ID
		while(checkId != true) {
			System.out.println("\nPlease enter the author ID:");
			authorId = MenuInterface.readInt();
			
			checkId = MenuInterface.ifNotExists(con, authorId, "authorId", "tbl_author");
		}
		
		System.out.println("Please enter the new author's name or N/A for no change:");
		authorName = MenuInterface.readString();
		
		if(!authorName.equalsIgnoreCase("N/A")) {
			authService.updateAuthor(con, authorId, authorName, "authorName");
		}
		
		System.out.println("\nAuthor updated successfully");
		MenuInterface.cont();
	}
	
	public void toDelete(Connection con){
		boolean checkId  = false;
		int authorId = 0;
		
		System.out.println();
		authService.viewAuthor(con);
		
		//Validate ID
		while(checkId != true) {
			System.out.println("\nPlease enter the author ID:");
			authorId = MenuInterface.readInt();
			
			checkId = MenuInterface.ifNotExists(con, authorId, "authorId", "tbl_author");
		}
		
		System.out.println("Warning: Deleting this author will delete all the books associated to it.");
		System.out.println("Enter Y to continue and N to go back to the previous menu:");
		choice = MenuInterface.readString();
		
		if(choice.equalsIgnoreCase("Y")) {
			authService.deleteAuthor(con, authorId);
			MenuInterface.cont();
		}
	}
	
	public void toView(Connection con) {
		System.out.println();
		authService.viewAuthor(con);
		MenuInterface.cont();
	}
}
