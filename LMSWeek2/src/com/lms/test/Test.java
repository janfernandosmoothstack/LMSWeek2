package com.lms.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.lms.Presentation.MainMenu;

public class Test {
	public static void main(String[] args) {
		Connection con = null;
		try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Iamsherlocked#2.0");
            
            MainMenu mainMenu = new MainMenu();
    		mainMenu.showMenu(con);
 
		} catch(Exception e) {
			System.out.println(e);
        } finally {
        	try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		
	}

}
