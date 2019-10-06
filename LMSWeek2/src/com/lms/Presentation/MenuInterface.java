package com.lms.Presentation;

import java.io.IOException;
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
				"Please select an option(1-5):";
		
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
	
	//Makes sure the ID does not exist
	public static boolean ifExists (Connection con, int id, String idFieldName, String tblName) {
		PreparedStatement ps = null;
		boolean checkId = false;
		
		try {
			String sql = "SELECT " + idFieldName + " FROM " + tblName
					+ " WHERE " + idFieldName + " = ?";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
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
	
	//Makes sure the ID exists
	public static boolean ifNotExists(Connection con, int id, String idFieldName, String tblName) {
		PreparedStatement ps = null;
		boolean checkId = false;
		
		try {
			String sql = "SELECT " + idFieldName + " FROM " + tblName
					+ " WHERE " + idFieldName + " = ?";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			//if that ID exists in that table
			if(rs.next()) {
				checkId = true;
			} else {
				System.out.println("ID does not exists.");
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return checkId;
	}
	
	public static void clr() {  
	    try {
	    	new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	    } catch(IOException | InterruptedException E) {
	    }
	}
}
