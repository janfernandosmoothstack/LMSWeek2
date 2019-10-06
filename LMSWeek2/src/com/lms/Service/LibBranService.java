package com.lms.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.lms.DAO.LibBranchDAO;

public class LibBranService {
	LibBranchDAO libBranDAO = new LibBranchDAO();
	
	public void createLibBran(Connection con, int branchId, String branchName, String branchAddress) {
		libBranDAO.writeLibBran(con, branchId, branchName, branchAddress);
		
		System.out.println("\nLibrary Branch created successfully.");
	}
	
	public void updateLibBran(Connection con, int branchId, String branchName, String branchAddress) {
		PreparedStatement ps = null;
		
		try {
			String sql = "UPDATE tbl_library_branch "
							+ "SET branchName = ?, branchAddress = ? "
							+ "WHERE branchId = ?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, branchName);
			ps.setString(2, branchAddress);
			ps.setInt(3, branchId);
			ps.executeUpdate();
			
		 	System.out.println("\nLibrary Branch updated successfully.");
		 	
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
	
	public void deleteLibBran() {
		
	}
	
	public void viewLibBran(Connection con) {
		libBranDAO.readLibBran(con);
	}
}
