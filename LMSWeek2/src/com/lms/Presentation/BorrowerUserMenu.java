package com.lms.Presentation;

import java.sql.Connection;
import com.lms.DAO.LoansDAO;
import com.lms.POJO.*;
import com.lms.Service.CopiesService;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

public class BorrowerUserMenu {
	static LoansDAO loans = new LoansDAO();
	static CopiesService transaction = new CopiesService();
	static MainMenu main = new MainMenu();
	
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
	public  void showMenu(Connection con, int cardNo) {
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
						main.showMenu(con);
						return;
						
					default:
						System.out.println("Please enter a valid option.");
						break;
				}
			}
		}while(choice != 3);
	}
	
	//checkout branch menu
	public void displayBranchCheckout(Connection con, int cardNo) {
		
        try {
            Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("select branchId, branchName from tbl_library_branch");
           System.out.println(" Branch Name");
           
           List<LibraryBranch> branch = new ArrayList<LibraryBranch>();
           int count = 1;
           while (rs.next())
             {
               LibraryBranch lb = new LibraryBranch();
                        lb.setBranchId(rs.getInt("branchId"));
                        lb.setBranchName(rs.getString("branchName"));
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
                    int bhId = branch.get(input-1).getBranchId();
                    check = true;
                    displayBranchBook(con, cardNo,  bhId);
                }
                 if (input == count)
                 {
                        check = true;
                        showMenu(con, cardNo);
                 }
                    else
                    {
                        System.out.println("Invalid selection, Please try again! Press '"+ count +"' to exit\n");
                    }
             }
        }
        catch(Exception e)
        {
            System.out.println(e);
		
		} 
    }
	
	
	// checkout book-list
		public void displayBranchBook(Connection con, int cardNo, int bhId) {
			
			 try {
				 PreparedStatement ps = con.prepareStatement("SELECT tbl_book.bookId, CONCAT(tbl_book.title, ' by ' , tbl_author.authorName) AS title  FROM tbl_book INNER JOIN tbl_author ON tbl_book.authId = tbl_author.authorId INNER JOIN tbl_book_copies ON tbl_book.bookId =  tbl_book_copies.bookId INNER JOIN tbl_library_branch ON tbl_book_copies.branchId = tbl_library_branch.branchId WHERE tbl_library_branch.branchId = ? AND tbl_book_copies.noOfCopies >=1 ");
		           ps.setInt (1, bhId);
		           ResultSet rs = ps.executeQuery();

		           List<Book> book = new ArrayList<Book>();
		           int count = 1;
		           while (rs.next())
		             {
		               Book b = new Book();
		                        b.setBookId(rs.getInt("bookId"));
		                        b.setTitle(rs.getString("title"));
		                         book.add(b);
		                        System.out.println(count + ") " + rs.getString("title"));
		                         count++;
		             }           
		           System.out.println(count + ") Quit to previous \n");
		           boolean check = false;
		           while(check!=true)
		           {
		                System.out.println("Please select the ID of the book you want to checkout: ");
		                
		                int input = MenuInterface.readInt();
		                
		                if(input < (count))
		                {
		                    int bkId = book.get(input-1).getBookId();
		                    check = true;
			    			 validateCheckout(con, cardNo, bhId, bkId);
		                }
		                 if (input == count)
		                 {
		                        check = true;
		                        showMenu(con, cardNo);
		                 }
		                    else
		                    {
		                        System.out.println("Invalid selection, Please try again! Press '"+ count +"' to exit\n");
		                    }
		             }
		        }
		        	 catch(Exception e)
		 	        {
		 	            System.out.println(e);
		 			
		 			} 
			
		}
		
		//final checkout after selecting book
		public  void validateCheckout( Connection con, int cardNo, int  bhId, int bkId) {
			
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
					}
			}
			 catch(Exception e)
	        {
	            System.out.println(e);
			
			} 
			
		}
		
	
	//return branch menu
	public  void displayBranchReturn(Connection con, int cardNo) {
		
		try {
            Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("select branchId, branchName from tbl_library_branch");
           System.out.println(" Branch Name");
           
           List<LibraryBranch> branch = new ArrayList<LibraryBranch>();
           int count = 1;
           while (rs.next())
             {
               LibraryBranch lb = new LibraryBranch();
                        lb.setBranchId(rs.getInt("branchId"));
                        lb.setBranchName(rs.getString("branchName"));
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
                    int bhId = branch.get(input-1).getBranchId();
                    check = true;
                    displayReturnBranchBook(con, cardNo,bhId) ;
                }
                 if (input == count)
                 {
                        check = true;
                        showMenu(con, cardNo);
                 }
                    else
                    {
                        System.out.println("Invalid selection, Please try again! Press '"+ count +"' to exit\n");
                    }
             }
        }
		 catch(Exception e)
        {
            System.out.println(e);
		
		} 
       
        
    }
	
	
	//return book-list
	public void displayReturnBranchBook(Connection con, int cardNo, int bhId) {
		
		 try {
	           PreparedStatement ps = con.prepareStatement("SELECT tbl_book.bookId, CONCAT(tbl_book.title, ' by ' , tbl_author.authorName)  AS title FROM tbl_book INNER JOIN tbl_author ON tbl_book.authId = tbl_author.authorId INNER JOIN tbl_book_loans ON tbl_book.bookId =  tbl_book_loans.bookId INNER JOIN tbl_borrower ON tbl_book_loans.cardNo = tbl_borrower.cardNo INNER JOIN tbl_library_branch ON tbl_book_loans.branchId = tbl_library_branch.branchId WHERE tbl_library_branch.branchId = ? AND tbl_borrower.cardNo =?");
	           ps.setInt (1, bhId);
	           ps.setInt (2, cardNo);
	           ResultSet rs = ps.executeQuery();

	           List<Book> book = new ArrayList<Book>();
	           int count = 1;
	           while (rs.next())
	             {
	               Book b = new Book();
	                        b.setBookId(rs.getInt("bookId"));
	                        b.setTitle(rs.getString("title"));
	                         book.add(b);
	                        System.out.println(count + ") " + rs.getString("title"));
	                         count++;
	             }           
	           System.out.println(count + ") Quit to previous \n");
	           boolean check = false;
	           while(check!=true)
	           {
	                System.out.println("Pick the Book Id you want to return:  ");
	                
	                int input = MenuInterface.readInt();
	                
	                if(input < (count))
	                {
	                    int bkId = book.get(input-1).getBookId();
	                    check = true;
	                    checkLoans(con, cardNo, bhId, bkId);
	                }
	                 if (input == count)
	                 {
	                        check = true;
	                        showMenu(con, cardNo);
	                 }
	                    else
	                    {
	                        System.out.println("Invalid selection, Please try again! Press '"+ count +"' to exit\n");
	                    }
	             }
	        }
		 catch(Exception e)
	        {
	            System.out.println(e);
			
			} 
		
	
    }
	
	
	//final return after selecting book
	public void checkLoans( Connection con, int cardNo, int  bhId,  int  bkId) {
			
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
						 showMenu(con, cardNo);
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
