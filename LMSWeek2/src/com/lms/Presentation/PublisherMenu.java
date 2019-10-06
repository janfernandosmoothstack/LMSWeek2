package com.lms.Presentation;

import java.sql.Connection;

import com.lms.Service.AuthorService;
import com.lms.Service.PublisherService;

public class PublisherMenu implements MenuInterface{
	PublisherService pubService = new PublisherService();
	
	public void showMenu(Connection con) {
		String choice = "";
		
		do {
			System.out.println("\nPublisher Menu");
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
		int pubId = 0;
		String pubName = "";
		String pubAddress ="";
		String pubPhone = "";
		
		//Validate ID
		while(checkId != true) {
			System.out.println("\nPlease enter the publisher ID:");
			pubId = MenuInterface.readInt();
			
			String sql = "SELECT publisherId FROM tbl_publisher "
							+ "WHERE publisherId = ?";
			
			checkId = MenuInterface.ifExists(con, pubId, sql);
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
			
			String sql = "SELECT publisherId FROM tbl_publisher "
							+ "WHERE publisherId = ?";
			
			checkId = MenuInterface.ifNotExists(con, pubId, sql);
		}
		
		System.out.println("Please enter the new publisher's name:");
		pubName = MenuInterface.readString();
		
		System.out.println("Please enter the new publisher's address:");
		pubAddress = MenuInterface.readString();
		
		System.out.println("Please enter the new publisher's phone number:");
		pubPhone = MenuInterface.readString();
		
		pubService.updatePub(con, pubId, pubName, pubAddress, pubPhone);
		MenuInterface.cont();
	}
	
	public void toDelete(Connection con){
		System.out.println("\nPlease enter the publisher ID:");
		int pubId = MenuInterface.readInt();
		//validate id
		
		//Call delete method
	}
	
	public void toView(Connection con) {
		System.out.println();
		pubService.viewPub(con);
		MenuInterface.cont();
	}
}
