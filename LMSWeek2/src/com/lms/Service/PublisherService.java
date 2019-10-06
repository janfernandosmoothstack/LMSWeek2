package com.lms.Service;

import java.sql.Connection;

import com.lms.DAO.PublisherDAO;

public class PublisherService {
	PublisherDAO pubDAO = new PublisherDAO();
	
	public void createPub(Connection con, int pubId, String pubName, String pubAddress, String pubPhone) {
		pubDAO.writeInsertPub(con, pubId, pubName, pubAddress, pubPhone);
		
		System.out.println("\nPublisher created successfully.");
	}
	
	public void updatePub(Connection con, int pubId, String newData, String fieldName) {
		pubDAO.writeUpdatePub(con, pubId, newData, fieldName);
	}
	
	public void deletePub(Connection con, int pubId) {
		pubDAO.writeDeletePub(con, pubId);
		
		System.out.println("\nPublisher deleted successfully.");
	}
	
	public void viewPub(Connection con) {
		pubDAO.readViewPub(con);
	}
}
