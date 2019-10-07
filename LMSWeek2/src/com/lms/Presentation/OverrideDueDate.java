package com.lms.Presentation;

import java.sql.Connection;
import java.sql.Date;

import com.lms.Service.LoansService;

public class OverrideDueDate {
	LoansService loans = new LoansService();
	
	public void override(Connection con) {
		boolean checkId = false;
		int cardNo = 0;
		int branchId = 0;
		int bookId = 0;
		
		System.out.println();
		loans.viewBookLoans(con);
		
		while(checkId != true) {
			System.out.println("\nPlease enter the card No.:");
			cardNo = MenuInterface.readInt();
			
			checkId = loans.ifCardNo(con, cardNo, checkId);
		}
		
		checkId = false;
		
		while(checkId != true) {
			System.out.println("Please enter the branch ID:");
			branchId = MenuInterface.readInt();
			
			checkId = loans.ifBranchId(con, branchId, checkId);
		}
		
		checkId = false;
		
		while(checkId != true) {
			System.out.println("Please enter the book ID:");
			bookId = MenuInterface.readInt();
			
			checkId = loans.ifBookId(con, bookId, checkId);
		}
		
		System.out.println("Please enter the number of days you would like to add to Due Date:");
		int days = MenuInterface.readInt();
		
		Date currDueDate = loans.dueDate(con, cardNo, bookId, branchId);
		
		loans.overDueDate(con, cardNo, bookId, branchId, currDueDate, days);
		
		System.out.println("Override successful.");
		
		System.out.println();
		loans.viewBookLoans(con);
		
		MenuInterface.cont();
	}

}
