package com.lms.Presentation;

import java.sql.Connection;

import com.lms.Service.PublisherService;

public class PublisherMenu implements MenuInterface{
	PublisherService pubService = new PublisherService();
	String choice = "";
	
	public void showMenu(Connection con) {
		do {
			MenuInterface.clr();
			
			System.out.println("Publisher Menu");
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
		int pubId = 0;
		String pubName = "";
		String pubAddress ="";
		String pubPhone = "";
		
		//Validate ID
		while(checkId != true) {
			System.out.println("\nPlease enter the publisher ID:");
			pubId = MenuInterface.readInt();
			
			checkId = MenuInterface.ifExists(con, pubId, "publisherId", "tbl_publisher");
		}
		
		System.out.println("Please enter the publisher's name:");
		pubName = MenuInterface.readString();
		
		System.out.println("Please enter the publisher's address:");
		pubAddress = MenuInterface.readString();
		
		System.out.println("Please enter the publisher's phone number:");
		pubPhone = MenuInterface.readString();
		
		pubService.createPub(con, pubId, pubName, pubAddress, pubPhone);
		MenuInterface.cont();
	}
	
	public void toUpdate(Connection con) {
		boolean checkId  = false;
		int pubId = 0;
		String pubName = "";
		String pubAddress ="";
		String pubPhone = "";
		
		System.out.println();
		pubService.viewPub(con);
		
		while(checkId != true){
			System.out.println("\nPlease enter the publisher ID:");
			pubId = MenuInterface.readInt();
			
			checkId = MenuInterface.ifNotExists(con, pubId, "publisherId", "tbl_publisher");
		}
		
		System.out.println("Please enter the new publisher's name or N/A for no change:");
		pubName = MenuInterface.readString();
		
		if(!pubName.equalsIgnoreCase("N/A")) {
			pubService.updatePub(con, pubId, pubName, "publisherName");
		}	
		
		System.out.println("Please enter the new publisher's address or N/A for no change:");
		pubAddress = MenuInterface.readString();
		
		if(!pubAddress.equalsIgnoreCase("N/A")) {
			pubService.updatePub(con, pubId, pubAddress, "publisherAddress");
		}
		
		System.out.println("Please enter the new publisher's phone number or N/A for no change:");
		pubPhone = MenuInterface.readString();
		
		if(!pubPhone.equalsIgnoreCase("N/A")) {
			pubService.updatePub(con, pubId, pubPhone, "publisherPhone");
		}
		
		System.out.println("\nPublisher updated successfully.");
		MenuInterface.cont();
	}
	
	public void toDelete(Connection con){
		boolean checkId  = false;
		int pubId = 0;
		
		System.out.println();
		pubService.viewPub(con);
		
		while(checkId != true){
			System.out.println("\nPlease enter the publisher ID:");
			pubId = MenuInterface.readInt();
			
			checkId = MenuInterface.ifNotExists(con, pubId, "publisherId", "tbl_publisher");
		}
		
		System.out.println("Warning: Deleting this publisher will delete all the books associated to it.");
		System.out.println("Enter Y to continue and N to go back to the previous menu:");
		choice = MenuInterface.readString();
		
		if(choice.equalsIgnoreCase("Y")) {
			pubService.deletePub(con, pubId);
			MenuInterface.cont();
		}
	}
	
	public void toView(Connection con) {
		System.out.println();
		pubService.viewPub(con);
		MenuInterface.cont();
	}
}