package com.lms.Presentation;

import java.sql.Connection;
import com.lms.DAO.LoansDAO;
import com.lms.Service.BorrowerService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class BorrowerUserMenu {
	static LoansDAO loans = new LoansDAO();
	static BorrowerService transaction = new BorrowerService();
	
	//gets card no
	public void readCardNo(Connection con) {
		System.out.print("Please enter your Card No:  " );
		int cardNo = MenuInterface.readInt();
		PreparedStatement ps = null;
		
		//validation if card No exists
		try {
				ps = con.prepareStatement("SELECT cardNo FROM tbl_borrower WHERE cardNo = ? ");
				ps.setInt (1, cardNo);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					showMenu(con, cardNo);
				} else {
					System.out.println("Invalid input ");
					readCardNo(con);
				}
		}
		   catch(Exception e)
        {
            System.out.println(e);
		
		}
	}
	
	//borrower main menu
	public static void showMenu(Connection con, int cardNo) {
		int choice = 0;
		
		do {
			System.out.println ("1) Checkout a book");
			System.out.println ("2) Return a book");
			System.out.println ("3) Back to Previous Main Menu");
			System.out.println ("Please enter your choice here: ");
			
			boolean checkChoice = false;
			
			//loop until user enters a valid choice
			while(checkChoice != true) {
				choice = MenuInterface.readInt();
				
				switch(choice) {
					case 1: //checkout
						displayBranchCheckout(con, cardNo);
						checkChoice = true;
						break;
					case 2: //Return
						displayBranchReturn(con, cardNo);
						checkChoice = true;
						break;
					
					case 3:
					//takes to Main Menu
						return;
						
					default:
						System.out.println("Please enter a valid option.");
						break;
				}
			}
		}while(choice != 3);
	}
	
	//checkout branch menu
	public static void displayBranchCheckout(Connection con, int cardNo) {
		
        try {
            Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("select branchId, branchName from tbl_library_branch");
           System.out.println("Branch ID \t Branch Name");
           
           while (rs.next())
             {
                 //Print one row
                 for(int i = 1 ; i <= 2; i++)
                 {
                       System.out.print(rs.getString(i) + "\t\t "); //Print one element of a row
                 }
                   System.out.println();//Move to the next line to print the next row.
             }
           
           System.out.println("0)Quit to cancel operation ");
           
           System.out.print("Pick the Branch Id you want to checkout from:  " );
    		 int bhId = MenuInterface.readInt();
    		 
    		 
    		 PreparedStatement ps = null;
    		//validation if branchId  exists
    			try {
    					ps = con.prepareStatement("SELECT branchId FROM tbl_library_branch WHERE branchId = ? ");
    					ps.setInt (1, bhId);
    					rs = ps.executeQuery();
    					if (rs.next()) {
    						displayBranchBook(con, cardNo,  bhId);
    					} else {
    						System.out.println("Invalid input ");
    						 showMenu(con, cardNo);
    					}
    			}
    			   catch(Exception e)
    	        {
    	            System.out.println(e);
    			
    			}
    		 
    		 if (bhId == 0) {
    			 showMenu(con, cardNo);
    		 }
    		 else {
    			 displayBranchBook(con, cardNo, bhId); 
    		 }
                              
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
	
	
	// checkout book-list
		public static void displayBranchBook(Connection con, int cardNo, int bhId) {
	        try {
	           PreparedStatement ps = con.prepareStatement("SELECT tbl_book.bookId, CONCAT(tbl_book.title, ' by ' , tbl_author.authorName)  FROM tbl_book INNER JOIN tbl_author ON tbl_book.authId = tbl_author.authorId INNER JOIN tbl_book_copies ON tbl_book.bookId =  tbl_book_copies.bookId INNER JOIN tbl_library_branch ON tbl_book_copies.branchId = tbl_library_branch.branchId WHERE tbl_library_branch.branchId = ? AND tbl_book_copies.noOfCopies >=1 ");
	           ps.setInt (1, bhId);
	           ResultSet rs = ps.executeQuery();

	           System.out.println("Book ID \t Book name by Author");
	           while (rs.next())
	             {
	                 //Print one row
	                 for(int i = 1 ; i <= 2; i++)
	                 {
	                       System.out.print(rs.getString(i) + "\t\t "); //Print one element of a row
	                 }
	                   System.out.println();//Move to the next line to print the next row.
	             }
	           System.out.println("0-> Quit to cancel operation ");
	           System.out.print("Pick the Book Id you want to checkout:  " );
	    		 int bkId = MenuInterface.readInt();
	    		 
	    		 if (bkId == 0) {
	    			 
	    			 showMenu(con, cardNo);
	    		 }
	    		 else {
	    			
	    			 validateCheckout(con, cardNo, bhId, bkId);
	    			 System.out.print("Checkout sucessful!" );
	    		 }
	                              
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        System.out.println("Book ID \t Book name by Author");
	          
	    }
		
		//final checkout after selecting book
		public static void validateCheckout( Connection con, int cardNo, int  bhId, int bkId) {
			
			PreparedStatement ps = null;
			try {
					ps = con.prepareStatement("SELECT * FROM tbl_book_loans WHERE cardNo = ? AND branchId = ? AND bookId = ? ");
					ps.setInt (1, cardNo);
					ps.setInt (2, bhId);
					ps.setInt (3, bkId);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						System.out.println ("You already have this book borrowed from this branch");
						showMenu(con, cardNo);
						
					} else {
						transaction.checkoutService(con,  bhId, bkId ) ;
						loans.writeLoans(con, cardNo, bhId, bkId);
						System.out.println ("You were able to sucessfully checkout a book.");
					}
			}
			   catch(Exception e)
	        {
	            System.out.println(e);
			
			} 
			
		}
		
	
	//return branch menu
	public static void displayBranchReturn(Connection con, int cardNo) {
		
        try {
            Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("select branchId, branchName from tbl_library_branch");
           System.out.println("Branch ID \t Branch Name");
           
           while (rs.next())
             {
                 //Print one row
                 for(int i = 1 ; i <= 2; i++)
                 {
                       System.out.print(rs.getString(i) + "\t\t "); //Print one element of a row
                 }
                   System.out.println();//Move to the next line to print the next row.
             }
           System.out.println("0-> Quit to cancel operation ");
           
           System.out.print("Pick the Branch Id you want to return to:  " );
    		 int bhId = MenuInterface.readInt();
    		 
    		 
    		 PreparedStatement ps = null;
     		//validation if branchId  exists
     			try {
     					ps = con.prepareStatement("SELECT branchId FROM tbl_library_branch WHERE branchId = ? ");
     					ps.setInt (1, bhId);
     					rs = ps.executeQuery();
     					if (rs.next()) {
     						displayReturnBranchBook(con, cardNo,  bhId);
     					} else {
     						System.out.println("Invalid input ");
     						 showMenu(con, cardNo);
     					}
     			}
     			   catch(Exception e)
     	        {
     	            System.out.println(e);
     			
     			}
    		 
    		 if (bhId == 0) {
    			 showMenu(con, cardNo);
    		 }
    		 else {
    			 displayReturnBranchBook(con, cardNo,bhId) ;
    		 }
                              
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
	
	
	//return book-list
	public static void displayReturnBranchBook(Connection con, int cardNo, int bhId) {
		
        try {
           PreparedStatement ps = con.prepareStatement("SELECT tbl_book.bookId, CONCAT(tbl_book.title, ' by ' , tbl_author.authorName) FROM tbl_book INNER JOIN tbl_author ON tbl_book.authId = tbl_author.authorId INNER JOIN tbl_book_loans ON tbl_book.bookId =  tbl_book_loans.bookId INNER JOIN tbl_borrower ON tbl_book_loans.cardNo = tbl_borrower.cardNo INNER JOIN tbl_library_branch ON tbl_book_loans.branchId = tbl_library_branch.branchId WHERE tbl_library_branch.branchId = ? AND tbl_borrower.cardNo =?");
           ps.setInt (1, bhId);
           ps.setInt (2, cardNo);
           ResultSet rs = ps.executeQuery();

           System.out.println("Book ID \t Book name by Author");
           while (rs.next())
             {
                 //Print one row
                 for(int i = 1 ; i <= 2; i++)
                 {
                       System.out.print(rs.getString(i) + "\t\t "); //Print one element of a row
                 }
                   System.out.println();//Move to the next line to print the next row.
             }
           System.out.println("0-> Quit to cancel operation ");
           System.out.print("Pick the Book Id you want to return:  " );
    		 int bkId = MenuInterface.readInt();
    		 
    		 if (bkId == 0) {
    			 showMenu(con, cardNo);
    		 }
    		 else {
    			 checkLoans(con, cardNo, bhId, bkId);
    			 System.out.println ("Return sucessful!");
    		 }
                              
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	//final return after selecting book
	public static void checkLoans( Connection con, int cardNo, int  bhId,  int  bkId) {
			
			PreparedStatement ps = null;
			try {
						//validation of bookId to return
						ps = con.prepareStatement("SELECT bookId FROM tbl_book_loans WHERE bookId = ? ");
						ps.setInt (1, bkId);
						 ps.executeQuery();
						 ResultSet rs2 = ps.executeQuery(); 
						 if (rs2.next()) {
							 transaction.returnService(con, bhId, bkId);
						ps = con.prepareStatement("DELETE FROM tbl_book_loans  WHERE cardNo = ? AND branchId = ? AND bookId = ? ");
						ps.setInt (1, cardNo);
						ps.setInt (2, bhId);
						ps.setInt (3, bkId);
						 ps.executeUpdate();
					} else {
						System.out.println ("Not a valid return");
					}
			}
					
			   catch(Exception e)
	        {
	            System.out.println(e);
			
			} 
			
		}
		
	}
