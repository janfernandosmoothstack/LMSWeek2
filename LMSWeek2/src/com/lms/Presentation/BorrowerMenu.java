package com.lms.Presentation;

import java.sql.Connection;

import com.lms.Service.BorrowerService;

public class BorrowerMenu implements MenuInterface {
	BorrowerService borrService = new BorrowerService();
	
	public void showMenu(Connection con) {
		String choice = "";
		
		do {
			MenuInterface.clr();
			
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
		boolean checkId  = false;
		int cardNo = 0;
		String name = "";
		String address ="";
		String phone = "";
		
		while(checkId != true) {
			System.out.println("\nPlease enter the borrower card No.:");
			cardNo = MenuInterface.readInt();
			//validate id
			
			String sql = "SELECT cardNo FROM tbl_borrower "
					+ "WHERE cardNo = ?";
	
			checkId = MenuInterface.ifExists(con, cardNo, sql);
		}
		
		System.out.println("Please enter the borrower's name:");
		name = MenuInterface.readString();
		
		System.out.println("Please enter the borrower's address:");
		address = MenuInterface.readString();
		
		System.out.println("Please enter the borrower's phone number:");
		phone = MenuInterface.readString();
		
		borrService.createBorr(con, cardNo, name, address, phone);
		MenuInterface.cont();
	}
	
	public void toUpdate(Connection con) {
		boolean checkId  = false;
		int cardNo = 0;
		String name = "";
		String address ="";
		String phone = "";
		
		System.out.println();
		borrService.viewBorr(con);
		
		while(checkId != true) {
			System.out.println("\nPlease enter the borrower card No.:");
			cardNo = MenuInterface.readInt();
			
			String sql = "SELECT cardNo FROM tbl_borrower "
					+ "WHERE cardNo = ?";
	
			checkId = MenuInterface.ifNotExists(con, cardNo, sql);
		}
		
		System.out.println("Please enter the new borrower's name:");
		name = MenuInterface.readString();
		
		System.out.println("Please enter the new borrower's address:");
		address = MenuInterface.readString();
		
		System.out.println("Please enter the new borrower's phone number:");
		phone = MenuInterface.readString();
		
		borrService.updateBorr(con, cardNo, name, address, phone);
		MenuInterface.cont();
	}
	
	public void toDelete(Connection con){
		System.out.println("\nPlease enter the borrower card No.:");
		int borrCardNo = MenuInterface.readInt();
		//validate id
		
		//Call delete method
	}
	
	public void toView(Connection con) {
		System.out.println();
		borrService.viewBorr(con);
		MenuInterface.cont();
	}
}
