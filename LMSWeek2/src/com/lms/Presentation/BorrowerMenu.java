package com.lms.Presentation;

import java.sql.Connection;

public class BorrowerMenu implements MenuInterface {
	public void showMenu(Connection con) {
		String choice = "";
		
		do {
			System.out.println("Borrower Menu");
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
		System.out.println("\nPlease enter the borrower card No.:");
		int borrCardNo = MenuInterface.readInt();
		//validate id
		
		System.out.println("Please enter the borrower's name:");
		String borrName = MenuInterface.readString();
		
		System.out.println("Please enter the borrower's address:");
		String borrAdress = MenuInterface.readString();
		
		System.out.println("Please enter the borrower's phone number:");
		String borrPhone = MenuInterface.readString();
		
		//call create method in service
	}
	
	public void toUpdate(Connection con) {
		System.out.println("\nPlease enter the borrower card No.:");
		int borrCardNo = MenuInterface.readInt();
		//validate id
		
		System.out.println("Please enter the new borrower's name:");
		String borrName = MenuInterface.readString();
		
		System.out.println("Please enter the new borrower's address:");
		String borrAdress = MenuInterface.readString();
		
		System.out.println("Please enter the new borrower's phone number:");
		String borrPhone = MenuInterface.readString();
		
		//Call update method in service
	}
	
	public void toDelete(Connection con){
		System.out.println("\nPlease enter the borrower card No.:");
		int borrCardNo = MenuInterface.readInt();
		//validate id
		
		//Call delete method
	}
	
	public void toView(Connection con) {
		
	}
}
