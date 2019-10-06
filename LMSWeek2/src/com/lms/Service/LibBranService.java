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
	
	public void deleteLibBran(Connection con, int libBranId) {
		libBranDAO.writeDeleteLibBran(con, libBranId);
		
		System.out.println("\nLibrary Branch deleted successfully.");
	}
	
	public void viewLibBran(Connection con) {
		libBranDAO.readViewLibBran(con);
	}
	
	//View available branches to dispatch books to
	public void viewDispatchBran(Connection con, int libBranId) {
		libBranDAO.readDispatchBran(con, libBranId);
	}
	
	public void dispatch(Connection con, int libBranId, int newBranId) {
		libBranDAO.dispatchBooks(con, libBranId, newBranId);
	}
}
