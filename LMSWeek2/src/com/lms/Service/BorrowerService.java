package com.lms.Service;

import java.sql.Connection;

import com.lms.DAO.BorrowerDAO;

public class BorrowerService extends Service{
	BorrowerDAO borrDAO = new BorrowerDAO();
	
	public void createBorr(Connection con, int cardNo, String name, String address, String phone) {
		borrDAO.writeBorr(con, cardNo, name, address, phone);
		
		System.out.println("\nBorrower created successfully.");
	}
	
	public void updateBorr(Connection con, int cardNo, String newData, String fieldName) {
		super.updateString(con, cardNo, newData, fieldName, "cardNo", "tbl_borrower");
	}
	
	public void deleteBorr() {
		
	}
	
	public void viewBorr(Connection con) {
		borrDAO.readBorr(con);
	}
}
