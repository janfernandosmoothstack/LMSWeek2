package com.lms.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.lms.DAO.BorrowerDAO;

public class BorrowerService {
	BorrowerDAO borrDAO = new BorrowerDAO();
	
	public void createBorr(Connection con, int cardNo, String name, String address, String phone) {
		borrDAO.writeBorr(con, cardNo, name, address, phone);
		
		System.out.println("\nBorrower created successfully.");
	}
	
	public void updateBorr(Connection con, int cardNo, String name, String address, String phone) {
		PreparedStatement ps = null;
		
		try {
			String sql = "UPDATE tbl_borrower "
							+ "SET name = ?, address = ?, phone = ? "
							+ "WHERE cardNo = ?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, address);
			ps.setString(3, phone);
			ps.setInt(4, cardNo);
			ps.executeUpdate();
			
		 	System.out.println("\nBorrower updated successfully.");
		 	
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
	
	public void deleteBorr() {
		
	}
	
	public void viewBorr(Connection con) {
		borrDAO.readBorr(con);
	}
}
