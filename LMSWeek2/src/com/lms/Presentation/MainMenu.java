package com.lms.Presentation;

public class MainMenu implements MenuInterface{
	public void showMenu() {
		String menu = "LMS Login\n" +
				"1. Librarian\n" +
				"2. Borrower\n" +
				"3. Admin\n" +
				"4. Exit\n" +
				"\n" +
				"Please select an option(1-3):";
		
		System.out.println(menu);
	}
}
