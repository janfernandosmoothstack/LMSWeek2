package com.lms.DAO;

import java.sql.Connection;
import java.util.Calendar;
import com.lms.Presentation.BorrowerUserMenu;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import  java.sql.Date;


public class LoansDAO {
	
	static BorrowerUserMenu bmenu = new BorrowerUserMenu();
	
	
	public void writeLoans(Connection con, int cardNo, int bhId, int bkId) {
		 Calendar c = Calendar.getInstance();
		Date startDate = new Date(c.getTime().getTime());
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

			System.out.println ("You were able to sucessfully checkout a book.");
			System.out.println ("");
			bmenu.showMenu(con, cardNo);
		 	
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
	
	public void UpdateBorrowerName(Connection con,  int brId, String brAddress) throws SQLException {
        PreparedStatement ps =  con.prepareStatement("UPDATE tbl_library_branch SET branchAddress =  ?"
                + " WHERE branchId = ?");
        ps.setString(1, brAddress);
        ps.setInt(2, brId);
        ps.executeUpdate();
    }
	
}
