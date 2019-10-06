package com.lms.Presentation;

import java.sql.Connection;

public class MainMenu implements MenuInterface{
	public void showMenu(Connection con) {
		String choice = "";
		
		do {
			String menu = "LMS Login\n" +
					"1. Librarian\n" +
					"2. Borrower\n" +
					"3. Admin\n" +
					"4. Exit\n" +
					"\n" +
					"Please select an option(1-4):";
			
			System.out.println(menu);
			
			boolean checkChoice = false;
			
			//loop until user enters a valid choice
			while(checkChoice != true) {
				choice = MenuInterface.readString();
				
				switch(choice) {
					case "1": //Librarian
						//LibrarianMenu librarian = new LibrarianMenu();
						//librarian.showMenu();
						//checkChoice = true;
						break;
					case "2": //Borrower
						checkChoice = true;
						break;
					case "3": //Admin
						//AdminMenu admin = new AdminMenu();
					//	admin.showMenu(con);
						
						checkChoice = true;
						break;
					case "4": //Exit
						return;
					default:
						System.out.println("Please enter a valid option.");
						break;
				}
			}
		} while(choice != "4");
	}
}