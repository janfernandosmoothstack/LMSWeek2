package com.lms.Service;

import java.sql.Connection;

import com.lms.DAO.BorrowerDAO;

public class BorrowerService {
	BorrowerDAO borrDAO = new BorrowerDAO();
	
	public void createBorr(Connection con, int cardNo, String name, String address, String phone) {
		borrDAO.writeInsertBorr(con, cardNo, name, address, phone);
		
		System.out.println("\nBorrower created successfully.");
	}
	
	public void updateBorr(Connection con, int cardNo, String newData, String fieldName) {
		borrDAO.writeUpdateBorr(con, cardNo, newData, fieldName);
	}
	
	public void deleteBorr() {
		
	}
	
	public void viewBorr(Connection con) {
		borrDAO.readViewBorr(con);
	}
}
