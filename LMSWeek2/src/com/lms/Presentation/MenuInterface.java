package com.lms.Presentation;

import java.util.Scanner;

public interface MenuInterface {
	public static Scanner input = new Scanner(System.in);
	
	public void showMenu();
	
	public static void crudMenu() {
		String menu = 
				"1. Create\n" +
				"2. Update\n" +
				"3. Delete\n" +
				"4. View" +
				"5. Exit\n" +
				"\n" +
				"Please select an option(1-3):";
		
		System.out.println(menu);
	}
	
	public static int readInt() {
		int inputNum = input.nextInt();
		return inputNum;
	}
	
	public static String readString() {
		String inputString = input.nextLine();
		return inputString;
	}
}
