package com.lms.DAO;

import java.sql.Connection;
import java.util.Calendar;
import com.lms.Presentation.BorrowerUserMenu;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoansDAO {
	
	public void writeLoans(Connection con, int cardNo, int bhId, int bkId) {
		 Calendar c = Calendar.getInstance();
	        java.sql.Date startDate = new java.sql.Date(c.getTime().getTime());
		PreparedStatement ps = null;
		
		try {
			String sql = "INSERT INTO tbl_book_loans(bookId, branchId, cardNo, dateOut, dueDate) "
							+ "VALUES (?, ?, ?, ?, DATE_ADD(?, INTERVAL 7 DAY))";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, bkId);
			ps.setInt(2, bhId);
			ps.setInt(3, cardNo);
			ps.setDate(4,  startDate);
			ps.setDate(5, startDate);
			ps.executeUpdate();
			BorrowerUserMenu.showMenu(con, cardNo);
		 	
		} catch (SQLException e) {
			System.out.println(e);
	} 
			finally {
		try {
				ps.close();
			} catch (SQLException e) {
			System.out.println(e);
			}
		}
	
	}
	
	
}
