package com.lms.Service;

import java.sql.Connection;

import com.lms.DAO.LibBranchDAO;

public class LibBranService {
	LibBranchDAO libBranDAO = new LibBranchDAO();
	
	public void createLibBran(Connection con, int branchId, String branchName, String branchAddress) {
		libBranDAO.writeInsertLibBran(con, branchId, branchName, branchAddress);
		
		System.out.println("\nLibrary Branch created successfully.");
	}
	
	public void updateLibBran(Connection con, int branchId, String newData, String fieldName) {
		libBranDAO.writeUpdateLibBran(con, branchId, newData, fieldName);
	}
	
	public void deleteLibBran() {
		
	}
	
	public void viewLibBran(Connection con) {
		libBranDAO.readViewLibBran(con);
	}
}
