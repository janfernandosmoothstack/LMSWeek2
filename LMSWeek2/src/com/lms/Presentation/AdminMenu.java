package com.lms.Presentation;

import java.sql.Connection;

public class AdminMenu implements MenuInterface {
	public void showMenu(Connection con) {
		String choice = "";
		
		do {
			String menu = "Admin Menu\n" +
					"1. Book\n" +
					"2. Author\n" +
					"3. Publisher\n" +
					"4. Library Branch\n" +
					"5. Borrower\n" +
					"6. Override Due Date\n" +
					"7. Exit\n" +
					"\n" +
					"Please select an option(1-7):";
			
			System.out.println(menu);
			
			boolean checkChoice = false;
			
			//loop until user enters a valid choice
			while(checkChoice != true) {
				choice = MenuInterface.readString();
				
				switch(choice) {
					case "1": //Book
						BookMenu bookMenu = new BookMenu();
						bookMenu.showMenu(con);
						
						checkChoice = true;
						break;
					case "2": //Author
						AuthorMenu authorMenu = new AuthorMenu();
						authorMenu.showMenu(con);
						
						checkChoice = true;
						break;
					case "3": //Publisher
						PublisherMenu pubMenu = new PublisherMenu();
						pubMenu.showMenu(con);
						
						checkChoice = true;
						break;
					case "4": //Library Branch
						LibraryBranchMenu libBranMenu = new LibraryBranchMenu();
						libBranMenu.showMenu(con);
						
						checkChoice = true;
						break;
					case "5": //Borrower
						BorrowerMenu borrowerMenu = new BorrowerMenu();
						borrowerMenu.showMenu(con);
						
						checkChoice = true;
						break;
					case "6": //Override
						
						checkChoice = true;
						break;
					case "7": //Exit
						return;
					default:
						System.out.println("Please enter a valid option.");
						break;
				}
			}
		} while(choice != "8");
	}
}
