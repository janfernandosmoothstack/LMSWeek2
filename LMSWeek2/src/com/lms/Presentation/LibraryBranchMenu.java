package com.lms.Presentation;

import java.sql.Connection;
import java.util.InputMismatchException;

import com.lms.Service.LibBranService;

public class LibraryBranchMenu implements MenuInterface {
	LibBranService libBranServ = new LibBranService();
	String choice = "";
	
	public void showMenu(Connection con) {
		do {
			System.out.println("\nLibrary Branch Menu");
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
		int libBranId = 0;
		String libBranName = "";
		String libBranAddress ="";
		
		try {
			while(checkId != true) {
				System.out.println("\nPlease enter a new the library branch ID:");
				libBranId = MenuInterface.readInt();
		
				checkId = libBranServ.ifExists(con, libBranId, checkId);
			}
		} catch(InputMismatchException e) {
			System.out.println("Please enter a number. Returning to previous menu...");
			MenuInterface.threadSleep();
			return;
		}
		
		System.out.println("Please enter the library branch's name:");
		libBranName = MenuInterface.readString();
		
		System.out.println("Please enter the library branch's address:");
		libBranAddress = MenuInterface.readString();
		
		libBranServ.createLibBran(con, libBranId, libBranName, libBranAddress);
		MenuInterface.cont();
	}
	
	public void toUpdate(Connection con) {
		boolean checkId  = false;
		int libBranId = 0;
		String libBranName = "";
		String libBranAddress ="";
		
		System.out.println();
		libBranServ.viewLibBran(con);
		
		try {
			while(checkId != true) {
				System.out.println("\nPlease enter a valid the library branch ID:");
				libBranId = MenuInterface.readInt();
		
				checkId = libBranServ.ifNotExists(con, libBranId, checkId);
			}
		} catch(InputMismatchException e) {
			System.out.println("Please enter a number. Returning to previous menu...");
			MenuInterface.threadSleep();
			return;
		}
		
		System.out.println("Please enter the new library branch's name or N/A for no change:");
		libBranName = MenuInterface.readString();
		
		if(libBranName.equalsIgnoreCase("N/A")) {
			libBranName = libBranServ.getData(con, libBranId, "branchName");
		}	
		
		System.out.println("Please enter the new library branch's address or N/A for no change:");
		libBranAddress = MenuInterface.readString();
		
		if(libBranAddress.equalsIgnoreCase("N/A")) {
			libBranAddress = libBranServ.getData(con, libBranId, "branchAddress");
		}
		
		libBranServ.updateLibBran(con, libBranId, libBranName, libBranAddress);
		MenuInterface.cont();
	}
	
	public void toDelete(Connection con){
		boolean checkId  = false;
		int libBranId = 0;
		int newBranId = 0;
		
		System.out.println();
		libBranServ.viewLibBran(con);
		
		try {
			while(checkId != true) {
				System.out.println("\nPlease enter a valid the library branch ID:");
				libBranId = MenuInterface.readInt();
		
				checkId = libBranServ.ifNotExists(con, libBranId, checkId);
			}
		} catch(InputMismatchException e) {
			System.out.println("Please enter a number. Returning to previous menu...");
			MenuInterface.threadSleep();
			return;
		}
		
		System.out.println("Warning: You are about to delete a library branch...");
		
		String menu = "1. Dispatch books to another branch\n" + 
						"2. Delete book copies associated with library branch\n" +
						"\n" +
						"Please select an option(1-2):";
		
		System.out.println(menu);
		choice = MenuInterface.readString();
		System.out.println();
		
		if (choice.equals("1")) {
			libBranServ.viewDispatchBran(con, libBranId);
			
			System.out.println("\nWhich branch would you like to dispatch the books to?");
			newBranId = MenuInterface.readInt();
			
			libBranServ.dispatch(con, libBranId, newBranId); //dispatch books to new branch
			libBranServ.deleteLibBran(con, libBranId); //delete library branch
		} else if (choice.equals("2")) {
			libBranServ.deleteLibBran(con, libBranId);
		}
		
		MenuInterface.cont();
	}
	
	public void toView(Connection con) {
		System.out.println();
		libBranServ.viewLibBran(con);
		MenuInterface.cont();
	}
}
