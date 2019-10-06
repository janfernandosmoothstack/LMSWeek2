package com.lms.Presentation;

import java.sql.Connection;

import com.lms.Service.OverrideService;

public class OverrideDueDate {
	OverrideService overService = new OverrideService();
	
	public void override(Connection con) {
		System.out.println("Book Loans");
		overService.readBookLoans(con);
		
		
		
	}

}
