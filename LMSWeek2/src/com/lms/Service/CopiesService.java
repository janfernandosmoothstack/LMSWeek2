package com.lms.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CopiesService {
	
		public void returnService(Connection con, int  bhId, int bkId ) {
			PreparedStatement ps = null;
			try {
					ps = con.prepareStatement("UPDATE tbl_book_copies SET noOfCopies = noOfCopies+1 WHERE bookId = ? AND branchId = ? ");
					ps.setInt (1, bkId);
					ps.setInt (2, bhId);
					 ps.executeUpdate();
					 System.out.println ("Return sucessful!");
					 System.out.println ("");
		}
			catch(Exception e)
	        {
	            System.out.println(e);
			
			} 
		}
		
		
		public void checkoutService(Connection con, int  bhId, int bkId ) {
			PreparedStatement ps = null;
			try {
				ps = con.prepareStatement("UPDATE tbl_book_copies SET noOfCopies = noOfCopies-1 WHERE bookId = ? AND branchId = ? ");
				ps.setInt (1, bkId);
				ps.setInt (2, bhId);
				 ps.executeUpdate();
			}
			catch(Exception e)
	        {
	            System.out.println(e);
			
	        }		
		} 
}