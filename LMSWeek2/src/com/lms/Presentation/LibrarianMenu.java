package com.lms.Presentation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.lms.POJO.Book;
import com.lms.POJO.LibraryBranch;

public class LibrarianMenu implements MenuInterface {
	
	public void showMenu(Connection con) {
		String choice = "";
		
		do {
			String menu = "Pick an option\n" +
					"---------------------------------------------\n" +
					"1. Enter the branch you manage\n" +
					"2. Exit\n\n" +
					"Please select an option(1-2):";
			
			System.out.println(menu);
			
			boolean checkChoice = false;
			
			//loop until user enters a valid choice
			while(checkChoice != true) {
				choice = MenuInterface.readString();
				
				switch(choice) {
					case "1":
						//Display branches
						Lib1(con);
						checkChoice = true;
			            
			            //validateBranchId(con, brId);
			           
						break;
					case "2":
						return;
					case "0": return;
					default:
						System.out.println("Please enter a valid option.");
						break;
				}
			}
		} while(choice != "2");
	}
	
	//LIB1 Menu
	public void Lib1(Connection con) {
		
		try {
			Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select branchId, branchName, branchAddress from tbl_library_branch");         
            System.out.println("\n   Branch Name");

        	List<LibraryBranch> branch = new ArrayList<LibraryBranch>();
        	int count = 1;
            while (rs.next())
              {
            	LibraryBranch lb = new LibraryBranch();
                		 lb.setBranchId(rs.getString("branchId"));
                		 lb.setBranchName(rs.getString("branchName"));
                		 lb.setBranchAddress(rs.getString("branchAddress"));
                		  branch.add(lb);
                		 System.out.println(count + ") " + rs.getString("branchName"));
                		  count++;

              }       	
            System.out.println(count + ") Quit to previous \n");
            
            boolean check = false;
            while(check!=true) 
            {
	            System.out.println("Please select the ID of the branch: ");
	            int input = MenuInterface.readInt();
	            if(input < (count)) 
	            {
	            	int brId = Integer.parseInt(branch.get(input-1).getBranchId());
	            	check = true;
	                validateBranchId(con, brId);
	            }
		         if (input == count)
		         {
		            	check = true;
		            	return;
		         }
		            else 
		            {
		            	System.out.println("Invalid selection, Please try again! Press '"+ count +"' to exit\n");
		            }
	         }
            
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//LIB2 Menu
	public static void Lib2(Connection con) {
		
	}
	
	public void showSubMenu(Connection con, int branchId, String brName) {
		String choice = "";
		
		do {
			String menu = "\nPick an option\n" +
		"---------------------------------------------\n" +
					"1. Update the details of the Library\n" +
					"2. Add copies of the book to the Branch\n" +
					"3. Quit to previous \n\n" +
					"Please select an option(1-3):";
			
			System.out.println(menu);
			
			boolean checkChoice = false;
			
			//loop until user enters a valid choice
			while(checkChoice != true) {
				choice = MenuInterface.readString();
				
				switch(choice) {
					case "1":
						//Update branch details
						updateBranch(con, branchId, brName);
						checkChoice = true;
						break;
					case "2":
						Lib4(con, branchId, brName);
						break;
					case "3": Lib1(con);
					default:
						System.out.println("Please enter a valid option.");
						break;
				}
			}
		} while(choice != "3");
	}
	
	// Validate Branch Address is the right option
	public void validateBranchId(Connection con, int brId){

          PreparedStatement ps = null;
          
		try {
			ps =  con.prepareStatement("select branchName from tbl_library_branch WHERE branchId = ?");
			ps.setInt(1, brId);
			ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					String brName = rs.getString(1);
					 showSubMenu(con, brId, brName);
				} else {
				    System.out.println("\nPlease enter a valid branch Id :-");
				    validateBranchId(con, brId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Update Branch 
		public void updateBranch(Connection con, int brId, String brName) {
			
			System.out.println("\n You have chose to update the Branch with Branch Id: "+brId+
					" and Branch Name: " +brName+"\nEnter ‘quit’ at any prompt to cancel operation.\n");
			
			System.out.println("Please enter new branch name or enter N/A for no change:");
			String newName = MenuInterface.readString();
			
			if(newName.equalsIgnoreCase("quit")) {
			    showSubMenu(con,brId,brName);
			}
			
			if(newName.equalsIgnoreCase("N/A")){
				UpdateOnlyBranchAddress(con, brId, brName, newName);
				}
			
			System.out.println("Please enter new branch address or enter N/A for no change:");
			String newAddress = MenuInterface.readString();
			
			if(newAddress.equalsIgnoreCase("quit")) {
			    showSubMenu(con,brId,brName);
			}
			
			if(newAddress.equalsIgnoreCase("N/A")){
				UpdateOnlyBranchName(con, brId, brName, newName);
			}
			
			if(newAddress.equalsIgnoreCase("N/A") && newName.equalsIgnoreCase("N/A")){
			    System.out.println("Update canceled");
			    showSubMenu(con,brId,brName);
			}
			
			try {
				PreparedStatement ps =  con.prepareStatement("Update tbl_library_branch SET branchName = ?, branchAddress = ? WHERE branchId = ?");
				ps.setString(1, newName);
				ps.setString(2, newAddress);
				ps.setInt(3, brId);
				ps.executeUpdate();
				
					    System.out.println("\nSuccessfully Updated");
					    showSubMenu(con,brId,brName);
					    
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	//Add copies
		public void Lib4(Connection con, int brId, String brName) {
			
			try {
				System.out.println("\nPick the Book you want to add copies of, to your branch:");
				System.out.println("-------------------------------------------------------------");
	            PreparedStatement ps = con.prepareStatement("SELECT tbl_book.bookId, CONCAT(tbl_book.title, ' by ' , tbl_author.authorName) AS title FROM tbl_book INNER JOIN tbl_author ON tbl_book.authId = tbl_author.authorId INNER JOIN tbl_book_copies ON tbl_book.bookId = tbl_book_copies.bookId INNER JOIN tbl_library_branch ON tbl_book_copies.branchId = tbl_library_branch.branchId WHERE tbl_library_branch.branchId = ? ");
				ps.setInt(1, brId);
	            ResultSet rs = ps.executeQuery();
	            
	            System.out.println("   Book Title");
	            List<Book> book = new ArrayList<Book>();
	        	int count = 1;
	            while (rs.next())
	              {
	            	Book bk = new Book();
	                		 bk.setBookId(rs.getString("bookId"));
	                		 bk.setTitle(rs.getString("title"));
	                		 book.add(bk);
	                		 System.out.println(count + ") " + rs.getString("title"));
	                		  count++;
	              }       	
	            System.out.println(count + ") Quit to previous \n");
	            
	            boolean check = false;
	            while(check!=true) 
	            {
	            	System.out.println("Please select the ID of the book: ");
	            	int input = MenuInterface.readInt();
		            if(input < (count)) 
		            {
		            	int bookId = Integer.parseInt(book.get(input-1).getBookId());
		            	check = true;
		            	validateBookId(con, brId, brName, bookId);
		            }
			         if (input == count)
			         {
			            	check = true;
			            	return;
			         }
			            else 
			            {
			            	System.out.println("Invalid selection, Please try again! Press '"+ count +"' to exit\n");
			            }
		         }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public void validateBookId(Connection con, int brId, String brName, int bookId) {
	          PreparedStatement ps = null;
	          
			try {
				ps =  con.prepareStatement("select bookId from tbl_book WHERE bookId = ?");
				ps.setInt(1, bookId);
				ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						getCopies(con, bookId, brId, brName);
					} else {
					    System.out.println("\nPlease enter a valid branch Id :-");
					    validateBookId(con, brId, brName, bookId);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public void addCopies(Connection con, int bookId, String copies, int brId, String brName) {
			System.out.println("\nExisting number of copies: "+copies);
			System.out.println("Enter new number of copies: ");
			int newCopy = MenuInterface.readInt();
			
			try {
				PreparedStatement ps =  con.prepareStatement("Update tbl_book_copies SET noOfCopies = ? WHERE bookId = ?");
				ps.setInt(1, newCopy);
				ps.setInt(2, bookId);
				ps.executeUpdate();
				
					    System.out.println("\nSuccessfully Updated");
					    showSubMenu(con,brId,brName);
					    
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//Get no. of Copies from Branch
		public void getCopies(Connection con, int bookId, int brId, String brName) {
			PreparedStatement ps = null;
	          
			try {
			ps =  con.prepareStatement("select noOfCopies from tbl_book_copies WHERE bookId = ? AND branchId = ?");
			ps.setInt(1, bookId);
			ps.setInt(2, brId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String copies = rs.getString(1);
				addCopies(con, bookId, copies, brId, brName);
			} else {
			    System.out.println("\nPlease enter a valid branch Id :-");
			    validateBookId(con, brId, brName, bookId);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
		}
		
			//Updating only Branch Address
			public void UpdateOnlyBranchAddress(Connection con, int brId, String brName, String newName) {
				
				System.out.println("Please enter new branch address or enter N/A for no change:");
				newName = MenuInterface.readString();
				
				if(newName.equalsIgnoreCase("quit")) {
				    showSubMenu(con,brId,brName);
				}
				
				if(newName.equalsIgnoreCase("N/A") && newName.equalsIgnoreCase("N/A")){
				    System.out.println("Update canceled");
				    showSubMenu(con,brId,brName);
				}
				
				try {
					PreparedStatement ps =  con.prepareStatement("Update tbl_library_branch SET branchAddress = ? WHERE branchId = ?");
					ps.setString(1, newName);
					ps.setInt(2, brId);
					ps.executeUpdate();
					
						    System.out.println("\nSuccessfully Updated the Address only");
						    showSubMenu(con,brId,brName);
						    
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			public void UpdateOnlyBranchName(Connection con, int brId, String brName, String newName) {
				try {
					PreparedStatement ps =  con.prepareStatement("Update tbl_library_branch SET branchName = ? WHERE branchId = ?");
					ps.setString(1, newName);
					ps.setInt(2, brId);
					ps.executeUpdate();
					
						    System.out.println("\nSuccessfully Updated the Branch Name Only");
						    showSubMenu(con,brId,brName);
						    
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
}

