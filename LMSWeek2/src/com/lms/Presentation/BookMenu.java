package com.lms.Presentation;

import java.sql.Connection;

public class BookMenu implements MenuInterface{
	public void showMenu(Connection con) {
		String choice = "";
		
		do {
			System.out.println("Book Menu");
			MenuInterface.crudMenu();
			
			boolean checkChoice = false;
			
			//loop until user enters a valid choice
			while(checkChoice != true) {
				choice = MenuInterface.readString();
				
				switch(choice) {
					case "1": //Create
						checkChoice = true;
						break;
					case "2": //Update
						checkChoice = true;
						break;
					case "3": //Delete
						checkChoice = true;
						break;
					case "4": //View
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
		System.out.println("\nPlease enter the author ID:");
		int authorId = MenuInterface.readInt();
		//validate id
		
		System.out.println("Please enter the author's name:");
		String authorName = MenuInterface.readString();
		
		//call create method in service
	}
	
	public void toUpdate(Connection con) {
		System.out.println("\nPlease enter the author ID:");
		int authorId = MenuInterface.readInt();
		//validate id
		
		System.out.println("Please enter the new author's name:");
		String authorName = MenuInterface.readString();
		
		//Call update method in service
	}
	
	public void toDelete(Connection con){
		System.out.println("\nPlease enter the author ID:");
		int authorId = MenuInterface.readInt();
		//validate id
		
		//Call delete method
	}
	
	public void toView(Connection con) {
		
	}
}
