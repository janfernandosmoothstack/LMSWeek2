package com.lms.Presentation;

import java.sql.Connection;

public class BorrowerUserMenu {
	
	public void readCardNo(Connection con) {
		
		System.out.print("Please enter your Card No:  " );
		int cardNo = MenuInterface.readInt();
		
		//validation if card No exists
		//if cardNo
		
		showMenu(con, cardNo);
		
	}
	
	public void showMenu(Connection con, int cardNo) {
		String choice = "";
		
		do {
			System.out.println("Borrower Menu");
			System.out.println ("1) Checkout a book");
			System.out.println ("2) Return a book");
			System.out.println ("3)	Quit to Previous ");
			System.out.print ("Please enter your choice here (number): ");
			
			boolean checkChoice = false;
			
			//loop until user enters a valid choice
			while(checkChoice != true) {
				choice = MenuInterface.readString();
				
				switch(choice) {
					case "1": //Checkout
						checkChoice = true;
						break;
					case "2": //Return
						checkChoice = true;
						break;
					
					case "3": //Exit
						return;
					default:
						System.out.println("Please enter a valid option.");
						break;
				}
			}
		}while(choice != "3");
	}
	

}
