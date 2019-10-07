package com.lms.Presentation;

import java.sql.Connection;
import java.util.InputMismatchException;

import com.lms.Service.AuthorService;
import com.lms.Service.BookService;
import com.lms.Service.LoansService;
import com.lms.Service.PublisherService;

public class BookMenu implements MenuInterface{
	BookService bookService = new BookService();
	LoansService loanServ = new LoansService();
	PublisherService pubService = new PublisherService();
	AuthorService authService = new AuthorService();
	
	String choice = "";
	
	public void showMenu(Connection con) {
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
		
		try {
			while(checkId != true) {
				System.out.println("\nPlease enter the book ID:");
				bookId = MenuInterface.readInt();
		
				checkId = bookService.ifExists(con, bookId, checkId);
			}
		} catch(InputMismatchException e) {
			System.out.println("Please enter a number. Returning to previous menu...");
			MenuInterface.threadSleep();
			return;
		}
		
		System.out.println("Please enter the book's title:");
		title = MenuInterface.readString();
		
		checkId = false;
		
		try {
			while(checkId != true) {
				System.out.println("Please enter the author's ID:");
				authId = MenuInterface.readInt();
		
				checkId = authService.ifNotExists(con, authId, checkId);
			}
		} catch(InputMismatchException e) {
			System.out.println("Please enter a number. Returning to previous menu...");
			MenuInterface.threadSleep();
			return;
		}
		
		checkId = false;
		
		try {
			while(checkId != true) {
				System.out.println("Please enter the publisher's ID:");
				pubId = MenuInterface.readInt();
		
				checkId = pubService.ifNotExists(con, pubId, checkId);
			}
		} catch(InputMismatchException e) {
			System.out.println("Please enter a number. Returning to previous menu...");
			MenuInterface.threadSleep();
			return;
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
		
		try {
			while(checkId != true) {
				System.out.println("\nPlease enter the book ID:");
				bookId = MenuInterface.readInt();
		
				checkId = bookService.ifNotExists(con, bookId, checkId);
			}
		} catch(InputMismatchException e) {
			System.out.println("Please enter a number. Returning to previous menu...");
			MenuInterface.threadSleep();
			return;
		}
		
		System.out.println("Please enter the new book's title or N/A for no change:");
		title = MenuInterface.readString();
		
		if(!title.equalsIgnoreCase("N/A")) {
			bookService.updateBookString(con, bookId, title, "title");
		}
		
		checkId = false;
		
		try {
			while(checkId != true) {
				System.out.println("Please enter the new author's ID or 0 for no change:");
				authId = MenuInterface.readInt();
				
				if(authId != 0) {
					checkId = authService.ifNotExists(con, authId, checkId);
					
					if (checkId == true) {
						bookService.updateBookInt(con, bookId, authId, "authId");
					}
				} else {
					checkId = true;
				}
			}
		} catch(InputMismatchException e) {
			System.out.println("Please enter a number. Returning to previous menu...");
			MenuInterface.threadSleep();
			return;
		}
		
		checkId = false;
		
		try {
			while(checkId != true) {
				System.out.println("Please enter the new publisher's ID or 0 for no change:");
				pubId = MenuInterface.readInt();
				
				if(pubId != 0) {
					checkId = pubService.ifNotExists(con, pubId, checkId);
					
					if (checkId == true) {
						bookService.updateBookInt(con, bookId, pubId, "pubId");
					}
				} else {
					checkId = true;
				}
			}
		} catch(InputMismatchException e) {
			System.out.println("Please enter a number. Returning to previous menu...");
			MenuInterface.threadSleep();
			return;
		}

		System.out.println("\nBook updated successfully.");
		MenuInterface.cont();
	}
	
	public void toDelete(Connection con){
		boolean checkId = false;
		int bookId = 0;
		
		try {
			while(checkId != true) {
				System.out.println("\nPlease enter the book ID:");
				bookId = MenuInterface.readInt();
		
				checkId = bookService.ifNotExists(con, bookId, checkId);
			}
		} catch(InputMismatchException e) {
			System.out.println("Please enter a number. Returning to previous menu...");
			MenuInterface.threadSleep();
			return;
		}
		
		checkId = loanServ.loansExist(con, bookId, "bookId");
		
		if(checkId == true) {
			System.out.println("Warning: This book is currently loaned out.");
			System.out.println("Enter Y to continue and N to go back to the previous menu:");
			choice = MenuInterface.readString();
			
			if(choice.equalsIgnoreCase("Y")) {
				bookService.deleteBook(con, bookId);
				MenuInterface.cont();
			}
		} else {
			bookService.deleteBook(con, bookId);
			MenuInterface.cont();
		}
	}
	
	public void toView(Connection con) {
		System.out.println();
		bookService.viewBook(con);
		MenuInterface.cont();
	}
}
