package com.lms.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.lms.POJO.Borrower;

import java.sql.Date;

public class LoansDAO {
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
			//BorrowerUserMenu.showMenu(con, cardNo);
		 	
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
	
	public boolean readLoans(Connection con, int id, String idFieldName) {
		PreparedStatement ps = null;
		boolean checkId = false;
		
		try {
			String sql = "SELECT * FROM tbl_book_loans "
					+ "WHERE " + idFieldName + " = " + id;
	        
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			//if that ID exists in that table
			if(rs.next()) {
				checkId = true;
			}
	        
		} catch (SQLException e) {
			System.out.print(e);
		}
		
		return checkId;
	}
}
