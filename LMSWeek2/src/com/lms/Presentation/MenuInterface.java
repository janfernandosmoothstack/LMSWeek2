package com.lms.Presentation;

import java.util.Scanner;

public interface MenuInterface {
	public static Scanner input = new Scanner(System.in);
	
	public void showMenu();
	
	public static int readInt() {
		int inputNum = input.nextInt();
		return inputNum;
	}
	
	public static String readString() {
		String inputString = input.nextLine();
		return inputString;
	}
}
