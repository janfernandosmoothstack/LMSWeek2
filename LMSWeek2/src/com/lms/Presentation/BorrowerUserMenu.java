package com.lms.Presentation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class BorrowerUserMenu {
	
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
	
	public void showMenu(Connection con, int cardNo) {
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
						DisplayBranchCheckout(con, cardNo);
						checkChoice = true;
						break;
					case 2: //Return
						DisplayBranchReturn(con, cardNo);
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
	
	public void DisplayBranchCheckout(Connection con, int cardNo) {
        try {
            Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("select branchId, branchName from tbl_library_branch");
           ResultSetMetaData rsmd = rs.getMetaData();
           int columnsNumber = rsmd.getColumnCount();
           System.out.println("Branch ID \t Branch Name");
           
           while (rs.next())
             {
                 //Print one row
                 for(int i = 1 ; i <= columnsNumber; i++)
                 {
                       System.out.print(rs.getString(i) + "\t\t "); //Print one element of a row
                 }
                   System.out.println();//Move to the next line to print the next row.
             }
           System.out.println("0)Quit to cancel operation ");
           System.out.print("Pick the Branch Id you want to checkout from:  " );
    		 int bhId = MenuInterface.readInt();
    		 
    		 if (bhId == 0) {
    			 showMenu(con, cardNo);
    		 }
    		 else {
    			 DisplayBranchBook(con, cardNo, bhId); 
    		 }
                              
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
	public void DisplayBranchReturn(Connection con, int cardNo) {
        try {
            Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("select branchId, branchName from tbl_library_branch");
           ResultSetMetaData rsmd = rs.getMetaData();
           int columnsNumber = rsmd.getColumnCount();
           System.out.println("Branch ID \t Branch Name");
           
           while (rs.next())
             {
                 //Print one row
                 for(int i = 1 ; i <= columnsNumber; i++)
                 {
                       System.out.print(rs.getString(i) + "\t\t "); //Print one element of a row
                 }
                   System.out.println();//Move to the next line to print the next row.
             }
           System.out.println("0)Quit to cancel operation ");
           System.out.print("Pick the Branch Id you want to return to:  " );
    		 int bhId = MenuInterface.readInt();
    		 
    		 if (bhId == 0) {
    			 showMenu(con, cardNo);
    		 }
    		 else {
    			 
    		 }
                              
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
	
	public void DisplayBranchBook(Connection con, int cardNo, int bhId) {
        try {
           PreparedStatement ps = con.prepareStatement("SELECT tbl_book.bookId, CONCAT(tbl_book.title, ' by ' , tbl_author.authorName)  FROM tbl_book INNER JOIN tbl_author ON tbl_book.authId = tbl_author.authorId INNER JOIN tbl_book_copies ON tbl_book.bookId =  tbl_book_copies.bookId INNER JOIN tbl_library_branch ON tbl_book_copies.branchId = tbl_library_branch.branchId WHERE tbl_library_branch.branchId = ? AND tbl_book_copies.noOfCopies >=1 ");
          
        	ps.setInt (1, bhId);
            ResultSet rs = ps.executeQuery();
           ResultSetMetaData rsmd = rs.getMetaData();
           int columnsNumber = rsmd.getColumnCount();

           System.out.println("Book ID \t Book name by Author");
           while (rs.next())
             {
                 //Print one row
                 for(int i = 1 ; i <= columnsNumber; i++)
                 {
                       System.out.print(rs.getString(i) + "\t\t "); //Print one element of a row
                 }
                   System.out.println();//Move to the next line to print the next row.
             }
                              
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Book ID \t Book name by Author");
          
    }
	
}
