package com.lms.Service;

import java.sql.Connection;
import java.util.List;

import com.lms.DAO.BorrowerDAO;
import com.lms.POJO.Borrower;

public class BorrowerService {
	BorrowerDAO borrDAO = new BorrowerDAO();
	
	public void createBorr(Connection con, int cardNo, String name, String address, String phone) {
		borrDAO.writeInsertBorr(con, cardNo, name, address, phone);
		
		System.out.println("\nBorrower created successfully.");
	}
	
	public void updateBorr(Connection con, int cardNo, String newData, String fieldName) {
		borrDAO.writeUpdateBorr(con, cardNo, newData, fieldName);
	}
	
	public void deleteBorr(Connection con, int cardNo) {
		borrDAO.writeDeleteBorr(con, cardNo);
		
		System.out.println("\nBorrower deleted successfully.");
	}
	
	public void viewBorr(Connection con) {
		borrDAO.readViewBorr(con);
	}
	
	public boolean ifExists(Connection con, int cardNo, boolean checkId) {
		List<Borrower> list = borrDAO.readBorr(con);
		
		for(Borrower i : list) {
			if(i.getCardNo() == cardNo) {
				return checkId = false;
			}
		}
		return checkId = true;
	}
	
	public boolean ifNotExists(Connection con, int cardNo, boolean checkId) {
		List<Borrower> list = borrDAO.readBorr(con);
		
		for(Borrower i : list) {
			if(i.getCardNo() == cardNo) {
				return checkId = true;
			}
		}
		return checkId = false;
	}
}
