package com.lms.Presentation;

import java.sql.Connection;

import com.lms.Service.BookService;

public class BookMenu implements MenuInterface{
	BookService bookService = new BookService();
	
	public void showMenu(Connection con) {
		String choice = "";
		
		do {
			MenuInterface.clr();
			
			System.out.println("Book Menu");
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
		boolean checkId = false;
		int bookId = 0;
		String title = "";
		int authId = 0;
		int pubId = 0;
		
		while(checkId != true) {
			System.out.println("\nPlease enter the book ID:");
			bookId = MenuInterface.readInt();
	
			checkId = MenuInterface.ifExists(con, bookId, "bookId", "tbl_book");
		}
		
		System.out.println("Please enter the book's title:");
		title = MenuInterface.readString();
		
		while(checkId != true) {
			System.out.println("Please enter the author's ID:");
			authId = MenuInterface.readInt();
	
			checkId = MenuInterface.ifNotExists(con, authId, "authorId", "tbl_author");
		}
		
		while(checkId != true) {
			System.out.println("Please enter the publisher's ID:");
			pubId = MenuInterface.readInt();
	
			checkId = MenuInterface.ifNotExists(con, pubId, "publisherId", "tbl_publisher");
		}
		
		bookService.createBook(con, bookId, title, authId, pubId);
		MenuInterface.cont();
	}
	
	public void toUpdate(Connection con) {
		boolean checkId = false;
		int bookId = 0;
		String title = "";
		int authId = 0;
		int pubId = 0;
		
		System.out.println();
		bookService.viewBook(con);
		
		while(checkId != true) {
			System.out.println("\nPlease enter the book ID:");
			bookId = MenuInterface.readInt();
	
			checkId = MenuInterface.ifNotExists(con, bookId, "bookId", "tbl_book");
		}
		
		System.out.println("Please enter the new book's title:");
		title = MenuInterface.readString();
		
		if(!title.equalsIgnoreCase("N/A")) {
			bookService.updateBook(con, pubId, title, "title");
		}
		
		//if(!title.equalsIgnoreCase("N/A")) {
			//bookService.updateBook(con, pubId, title, "title");
		//}
		
		while(checkId != true) {
			System.out.println("Please enter the new author's ID:");
			authId = MenuInterface.readInt();
	
			checkId = MenuInterface.ifNotExists(con, authId, "authorId", "tbl_author");
		}
		
		while(checkId != true) {
			System.out.println("Please enter the new publisher's ID:");
			pubId = MenuInterface.readInt();
	
			checkId = MenuInterface.ifNotExists(con, pubId, "publisherId", "tbl_publisher");
		}

		MenuInterface.cont();
	}
	
	public void toDelete(Connection con){
		System.out.println("\nPlease enter the author ID:");
		int authorId = MenuInterface.readInt();
		//validate id
		
		//Call delete method
	}
	
	public void toView(Connection con) {
		System.out.println();
		bookService.viewBook(con);
		MenuInterface.cont();
	}
}
