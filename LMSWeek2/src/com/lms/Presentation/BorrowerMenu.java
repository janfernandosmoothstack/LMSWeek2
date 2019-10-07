package com.lms.Presentation;

import java.sql.Connection;
import java.util.InputMismatchException;

import com.lms.Service.BorrowerService;
import com.lms.Service.LoansService;

public class BorrowerMenu implements MenuInterface {
	BorrowerService borrService = new BorrowerService();
	LoansService loanServ = new LoansService();
	String choice = "";
	
	public void showMenu(Connection con) {
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
		
		try {
			while(checkId != true) {
				System.out.println("\nPlease enter the borrower card No.:");
				cardNo = MenuInterface.readInt();
		
				checkId = borrService.ifExists(con, cardNo, checkId);
			}
		} catch(InputMismatchException e) {
			System.out.println("Please enter a number. Returning to previous menu...");
			MenuInterface.threadSleep();
			return;
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
		
		try {
			while(checkId != true) {
				System.out.println("\nPlease enter the borrower card No.:");
				cardNo = MenuInterface.readInt();
		
				checkId = borrService.ifNotExists(con, cardNo, checkId);
			}
		} catch(InputMismatchException e) {
			System.out.println("Please enter a number. Returning to previous menu...");
			MenuInterface.threadSleep();
			return;
		}
		
		System.out.println("Please enter the new borrower's name:");
		name = MenuInterface.readString();
		
		if(!name.equalsIgnoreCase("N/A")) {
			borrService.updateBorr(con, cardNo, name, "name");
		}	
		
		System.out.println("Please enter the new borrower's address:");
		address = MenuInterface.readString();
		
		if(!address.equalsIgnoreCase("N/A")) {
			borrService.updateBorr(con, cardNo, address, "address");
		}	
		
		System.out.println("Please enter the new borrower's phone number:");
		phone = MenuInterface.readString();
		
		if(!phone.equalsIgnoreCase("N/A")) {
			borrService.updateBorr(con, cardNo, phone, "phone");
		}
		
		System.out.println("\nBorrower updated successfully.");
		MenuInterface.cont();
	}
	
	public void toDelete(Connection con){
		boolean checkId  = false;
		int cardNo = 0;
		
		System.out.println();
		borrService.viewBorr(con);
		
		try {
			while(checkId != true) {
				System.out.println("\nPlease enter the borrower card No.:");
				cardNo = MenuInterface.readInt();
		
				checkId = borrService.ifNotExists(con, cardNo, checkId);
			}
		} catch(InputMismatchException e) {
			System.out.println("Please enter a number. Returning to previous menu...");
			MenuInterface.threadSleep();
			return;
		}
		
		checkId = loanServ.loansExist(con, cardNo, "cardNo");
		
		if(checkId == true) {
			System.out.println("Warning: This borrower has book loans.");
			System.out.println("Enter Y to continue and N to go back to the previous menu:");
			choice = MenuInterface.readString();
			
			if(choice.equalsIgnoreCase("Y")) {
				borrService.deleteBorr(con, cardNo);
				MenuInterface.cont();
			}
		} else {
			borrService.deleteBorr(con, cardNo);
			MenuInterface.cont();
		}
	}
	
	public void toView(Connection con) {
		System.out.println();
		borrService.viewBorr(con);
		MenuInterface.cont();
	}
}
