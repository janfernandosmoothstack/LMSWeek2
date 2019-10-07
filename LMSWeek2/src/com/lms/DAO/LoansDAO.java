package com.lms.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.lms.POJO.Book;
import com.lms.POJO.BookLoans;
import com.lms.POJO.Borrower;
import com.lms.POJO.LibraryBranch;

import java.sql.Date;

public class LoansDAO {
	static BorrowerUserMenu bmenu = new BorrowerUserMenu();

	public void writeLoans(Connection con, int cardNo, int bhId, int bkId) {
		 Calendar c = Calendar.getInstance();
		Date startDate = new Date(c.getTime().getTime());
		PreparedStatement ps = null;
		
		try {
			String sql = "INSERT INTO tbl_book_loans(bookId, branchId, cardNo, dateOut, dueDate) "
							+ "VALUES (?, ?, ?, ?, DATE_ADD(?, INTERVAL 7 DAY))";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, bkId);
			ps.setInt(2, bhId);
			ps.setInt(3, cardNo);
			ps.setDate(4,  startDate);
			ps.setDate(5, startDate);
			ps.executeUpdate();

			System.out.println ("You were able to sucessfully checkout a book.");
			System.out.println ("");
			bmenu.showMenu(con, cardNo);
		 	
		} catch (SQLException e) {
			System.out.println(e);
	} 
			finally {
		try {
				ps.close();
			} catch (SQLException e) {
			System.out.println(e);
			}
		}
	
	}
	
	public boolean readExistsLoans(Connection con, int id, String idFieldName) {
		PreparedStatement ps = null;
		boolean checkId = false;
		
		try {
			String sql = "SELECT * FROM tbl_book_loans "
					+ "WHERE " + idFieldName + " = " + id;
	        
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			//if that ID exists in that table
			if(rs.next()) {
				checkId = true;
			}
	        
		} catch (SQLException e) {
			System.out.print(e);
		}
		
		return checkId;
	}
	
	public void readBookLoans(Connection con) {
		try {
			String sql = "SELECT * FROM tbl_book_loans";
	        
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int columnsNumber = rsmd.getColumnCount();
	        
	        System.out.println("BookId\t\tBranchId\t\tCardNo\t\tDateOut\t\tDueDate");
	    
	        while (rs.next()) {
	            //Print one row
	            for(int i = 1 ; i <= columnsNumber; i++) {
	            	System.out.print(rs.getString(i) + "\t\t"); //Print one element of a row
	            }
	              	
	            System.out.println();//Move to the next line to print the next row.
	    	}
	        
		} catch (SQLException e) {
			System.out.print(e);
		}
	}
	
	//read table into list
	public List<BookLoans> readBLoans(Connection con) {
		List<BookLoans> list = null;
		
		try {
			Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT bookId, branchId, cardNo, dateOut, dueDate FROM tbl_book_loans");         

            list = new ArrayList<BookLoans>();
        	
            while (rs.next()) {
            	BookLoans bookLoan = new BookLoans();
            	
            	Book book = new Book();
            	book.setBookId(rs.getInt("bookId"));
            	bookLoan.setBook(book);
            	
            	LibraryBranch lb = new LibraryBranch();
            	lb.setBranchId(rs.getInt("branchId"));
            	bookLoan.setBranch(lb);
            	
            	Borrower borr = new Borrower();
            	borr.setCardNo(rs.getInt("cardNo"));
            	bookLoan.setBorrower(borr);
            	
            	bookLoan.setDateOut(rs.getDate("dateOut"));
            	bookLoan.setDueDate(rs.getDate("dueDate"));
            	
            	list.add(bookLoan);
            }
        } catch (SQLException e) {
        	System.out.println(e);
        }
		
		return list;
	}
	
	public void overrideDueDate(Connection con, int cardNo, int bookId, int branchId, Date dueDate, int days) {
		PreparedStatement ps = null;
		
		try {
			String sql = "UPDATE tbl_book_loans "
							+ " SET dueDate = DATE_ADD(?, INTERVAL ? DAY) "
							+ "WHERE cardNo = ? "
							+ "AND bookId = ? "
							+ "AND branchID = ?";
			
			ps = con.prepareStatement(sql);
			ps.setDate(1, dueDate);
			ps.setInt(2, days);
			ps.setInt(3, cardNo);
			ps.setInt(4, bookId);
			ps.setInt(5, branchId);
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}
	
	public Date getDueDate(Connection con, int cardNo, int bookId, int branchId) {
		PreparedStatement ps = null;
		Date dueDate = null;
		
		try {
			String sql = "SELECT dueDate FROM tbl_book_loans "
					+ "WHERE bookId = ? "
					+ "AND branchId = ? "
					+ "AND cardNo = ?";
	        
			ps = con.prepareStatement(sql);
			ps.setInt(1, bookId);
			ps.setInt(2, branchId);
			ps.setInt(3, cardNo);
			
	        ResultSet rs = ps.executeQuery();
	        
	        while(rs.next()) {
	        	dueDate = rs.getDate("dueDate");
	        }
	        
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		
		return dueDate;
	}
}
