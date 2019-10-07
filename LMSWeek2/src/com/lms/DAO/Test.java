package com.lms.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;

import com.lms.Presentation.BorrowerUserMenu;

public class Test {
	public static void main(String[] args) {
		Connection con = null;
		try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","TapHouse123");
            
            BorrowerUserMenu borrower = new BorrowerUserMenu();
            borrower.readCardNo(con);
 
		} catch(SQLException e) {
			System.out.println(e);
        }
		catch(InputMismatchException e)
        {
                 System.out.println("Please enter an Integer, returning to previous menu");
                
        }
		finally {
        	try {
				con.close();
			} 
        	catch (SQLException e) {
				System.out.println(e);
			}
        	
		}	
	}
}