package com.lms.Service;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.lms.DAO.LoansDAO;
import com.lms.POJO.BookLoans;

public class LoansService {
	LoansDAO loans = new LoansDAO();
	
	public boolean loansExist(Connection con, int id, String idFieldName) {
		return loans.readExistsLoans(con, id, idFieldName);
	}
	
	public void viewBookLoans(Connection con) {
		loans.readBookLoans(con);
	}
	
	public Date dueDate(Connection con, int cardNo, int bookId, int branchId) {
		return loans.getDueDate(con, cardNo, bookId, branchId);		
	}
	
	public void overDueDate(Connection con, int cardNo, int bookId, int branchId, Date currDueDate, int days) {
		loans.overrideDueDate(con, cardNo, bookId, branchId, currDueDate, days);
	}
	
	public boolean ifCardNo(Connection con, int cardNo, boolean checkId) {
		List<BookLoans> list = loans.readBLoans(con);
		
		for(BookLoans i : list) {
			if(i.getBorrower().getCardNo() == cardNo) {
				return checkId = true;
			}
		}
		return checkId = false;
	}
	
	public boolean ifBranchId(Connection con, int branchId, boolean checkId) {
		List<BookLoans> list = loans.readBLoans(con);
		
		for(BookLoans i : list) {
			if(i.getBranch().getBranchId() == branchId) {
				return checkId = true;
			}
		}
		return checkId = false;
	}
	
	public boolean ifBookId(Connection con, int bookId, boolean checkId) {
		List<BookLoans> list = loans.readBLoans(con);
		
		for(BookLoans i : list) {
			if(i.getBook().getBookId() == bookId) {
				return checkId = true;
			}
		}
		return checkId = false;
	}
}
