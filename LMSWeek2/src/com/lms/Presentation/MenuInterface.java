package com.lms.Presentation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public interface MenuInterface {
	public static Scanner input = new Scanner(System.in);
	
	public void showMenu(Connection con);
	
	public static void crudMenu() {
		String menu = 
				"1. Create\n" +
				"2. Update\n" +
				"3. Delete\n" +
				"4. View\n" +
				"5. Exit\n" +
				"\n" +
				"Please select an option(1-3):";
		
		System.out.println(menu);
	}
	
	public static int readInt() {
		int inputNum = input.nextInt();
		input.nextLine();
		return inputNum;
	}
	
	public static String readString() {
		String inputString = input.nextLine();
		return inputString;
	}
	
	public static void cont() {
		System.out.println("\nPress any key to continue...");
		String key = readString();
		return;
	}
	
	public static boolean ifExists (Connection con, int Id, String sql) {
		PreparedStatement ps = null;
		boolean checkId = false;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, Id);
			ResultSet rs = ps.executeQuery();
			
			//if that ID exists in that table
			if(rs.next()) {
				System.out.println("ID already exists.");
			} else {
				checkId = true;
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return checkId;
	}
	
	//public static boolean ifNotExists(Connection con, int Id, String sql)
}
