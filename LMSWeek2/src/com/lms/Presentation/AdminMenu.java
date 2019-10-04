package com.lms.Presentation;

public class AdminMenu implements MenuInterface {
	public void showMenu() {
		String choice = "";
		
		do {
			String menu = "LMS Admin Menu\n" +
					"1. Book\n" +
					"2. Author\n" +
					"3. Publisher\n" +
					"4. Library Branches\n" +
					"5. Borrower\n" +
					"6. Override Due Date" +
					"7. Exit\n" +
					"\n" +
					"Please select an option(1-3):";
			
			System.out.println(menu);
			
			boolean checkChoice = false;
			
			//loop until user enters a valid choice
			while(checkChoice != true) {
				choice = MenuInterface.readString();
				
				switch(choice) {
					case "1":
						//authorMenu(authorList, bookList);
						checkChoice = true;
						break;
					case "2":
						//bookMenu(authorList, bookList, pubList);
						checkChoice = true;
						break;
					case "3":
						//pubMenu(bookList, pubList);
						checkChoice = true;
						break;
					case "4":
						return;
					default:
						System.out.println("Please enter a valid option.");
						break;
				}
			}
		} while(choice != "4");
	}
}
