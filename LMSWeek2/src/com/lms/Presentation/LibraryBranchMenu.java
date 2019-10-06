package com.lms.Presentation;

import java.sql.Connection;

import com.lms.Service.LibBranService;

public class LibraryBranchMenu implements MenuInterface {
	LibBranService libBranServ = new LibBranService();
	
	public void showMenu(Connection con) {
		String choice = "";
		
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
		String libBranAdress ="";
		
		while(checkId != true) {
			System.out.println("\nPlease enter the library branch ID:");
			libBranId = MenuInterface.readInt();
			
			String sql = "SELECT branchId FROM tbl_library_branch "
					+ "WHERE branchId = ?";
	
			checkId = MenuInterface.ifExists(con, libBranId, sql);
		}
		
		System.out.println("Please enter the library branch's name:");
		libBranName = MenuInterface.readString();
		
		System.out.println("Please enter the library branch's address:");
		libBranAdress = MenuInterface.readString();
		
		libBranServ.createLibBran(con, libBranId, libBranName, libBranAdress);
		MenuInterface.cont();
	}
	
	public void toUpdate(Connection con) {
		boolean checkId  = false;
		int libBranId = 0;
		String libBranName = "";
		String libBranAdress ="";
		
		System.out.println();
		libBranServ.viewLibBran(con);
		
		while(checkId != true) {
			System.out.println("\nPlease enter the library branch ID:");
			libBranId = MenuInterface.readInt();
			
			String sql = "SELECT branchId FROM tbl_library_branch "
					+ "WHERE branchId = ?";
	
			checkId = MenuInterface.ifNotExists(con, libBranId, sql);
		}
		
		System.out.println("Please enter the new library branch's name:");
		libBranName = MenuInterface.readString();
		
		System.out.println("Please enter the new library branch's address:");
		libBranAdress = MenuInterface.readString();
		
		libBranServ.updateLibBran(con, libBranId, libBranName, libBranAdress);
		MenuInterface.cont();
	}
	
	public void toDelete(Connection con){
		System.out.println("\nPlease enter the library branch ID:");
		int libBranId = MenuInterface.readInt();
		//validate id
		
		//Call delete method
	}
	
	public void toView(Connection con) {
		System.out.println();
		libBranServ.viewLibBran(con);
		MenuInterface.cont();
	}
}
