package com.lms.Service;

import java.sql.Connection;

import com.lms.DAO.LibBranchDAO;

public class LibBranService extends Service {
	LibBranchDAO libBranDAO = new LibBranchDAO();
	
	public void createLibBran(Connection con, int branchId, String branchName, String branchAddress) {
		libBranDAO.writeLibBran(con, branchId, branchName, branchAddress);
		
		System.out.println("\nLibrary Branch created successfully.");
	}
	
	public void updateLibBran(Connection con, int branchId, String newData, String fieldName) {
		super.updateString(con, branchId, newData, fieldName, "branchId", "tbl_library_branch");
	}
	
	public void deleteLibBran() {
		
	}
	
	public void viewLibBran(Connection con) {
		libBranDAO.readLibBran(con);
	}
}
