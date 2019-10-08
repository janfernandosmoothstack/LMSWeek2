package com.lms.Service;

import java.sql.Connection;
import java.util.List;

import com.lms.DAO.LibBranchDAO;
import com.lms.POJO.LibraryBranch;

public class LibBranService {
	LibBranchDAO libBranDAO = new LibBranchDAO();
	
	public void createLibBran(Connection con, int branchId, String branchName, String branchAddress) {
		libBranDAO.writeInsertLibBran(con, branchId, branchName, branchAddress);
		
		System.out.println("\nLibrary Branch created successfully.");
	}
	
	public void updateLibBran(Connection con, int branchId, String branName, String branAddress) {
		libBranDAO.writeUpdateLibBran(con, branchId, branName, branName);
		
		System.out.println("\nLibrary Branch updated successfully.");
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
	
	public String getData(Connection con, int id, String fieldName) {
		return libBranDAO.getBranData(con, id, fieldName);
	}
	
	public boolean ifExists(Connection con, int libBranId, boolean checkId) {
		List<LibraryBranch> list = libBranDAO.readLibBran(con);
		
		for(LibraryBranch i : list) {
			if(i.getBranchId() == libBranId) {
				return checkId = false;
			}
		}
		return checkId = true;
	}
	
	public boolean ifNotExists(Connection con, int libBranId, boolean checkId) {
		List<LibraryBranch> list = libBranDAO.readLibBran(con);
		
		for(LibraryBranch i : list) {
			if(i.getBranchId() == libBranId) {
				return checkId = true;
			}
		}
		return checkId = false;
	}
}
