package com.lms.Presentation;

public class LibraryBranchMenu implements MenuInterface {
	public void showMenu() {
		String choice = "";
		
		do {
			System.out.println("Library Branch Menu");
			MenuInterface.crudMenu();
			
			boolean checkChoice = false;
			
			//loop until user enters a valid choice
			while(checkChoice != true) {
				choice = MenuInterface.readString();
				
				switch(choice) {
					case "1": //Create
						toCreate();
						checkChoice = true;
						break;
					case "2": //Update
						toUpdate();
						checkChoice = true;
						break;
					case "3": //Delete
						toDelete();
						checkChoice = true;
						break;
					case "4": //View
						toView();
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
	
	public void toCreate() {
		System.out.println("\nPlease enter the library branch ID:");
		int libBranId = MenuInterface.readInt();
		//validate id
		
		System.out.println("Please enter the library branch's name:");
		String libBranName = MenuInterface.readString();
		
		System.out.println("Please enter the library branch's address:");
		String libBranAdress = MenuInterface.readString();
		
		//call create method in service
	}
	
	public void toUpdate() {
		System.out.println("\nPlease enter the library branch ID:");
		int libBranId = MenuInterface.readInt();
		//validate id
		
		System.out.println("Please enter the new library branch's name:");
		String libBranName = MenuInterface.readString();
		
		System.out.println("Please enter the new library branch's address:");
		String libBranAdress = MenuInterface.readString();
		
		//Call update method in service
	}
	
	public void toDelete(){
		System.out.println("\nPlease enter the library branch ID:");
		int libBranId = MenuInterface.readInt();
		//validate id
		
		//Call delete method
	}
	
	public void toView() {
		
	}
}
