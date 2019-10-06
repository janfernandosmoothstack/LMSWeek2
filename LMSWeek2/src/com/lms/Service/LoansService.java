package com.lms.Service;

import java.sql.Connection;

import com.lms.DAO.LoansDAO;

public class LoansService {
	LoansDAO loans = new LoansDAO();
	
	public boolean loansExist(Connection con, int id, String idFieldName) {
		return loans.readLoans(con, id, idFieldName);
	}
}
