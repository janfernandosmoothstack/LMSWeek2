package com.lms.Service;

import java.sql.Connection;

import com.lms.DAO.PublisherDAO;

public class PublisherService extends Service {
	PublisherDAO pubDAO = new PublisherDAO();
	
	public void createPub(Connection con, int pubId, String pubName, String pubAddress, String pubPhone) {
		pubDAO.writePub(con, pubId, pubName, pubAddress, pubPhone);
		
		System.out.println("\nPublisher created successfully.");
	}
	
	public void updatePub(Connection con, int pubId, String newData, String fieldName) {
		super.updateString(con, pubId, newData, fieldName, "publisherId", "tbl_publisher");
	}
	
	public void deletePub(Connection con, int pubId) {
		super.delete(con, pubId, "pubId", "tbl_book");
		super.delete(con, pubId, "publisherId", "tbl_publisher");
		
		System.out.println("\nPublisher deleted successfully.");
	}
	
	public void viewPub(Connection con) {
		pubDAO.readPub(con);
	}
}
