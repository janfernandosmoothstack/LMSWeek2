package com.lms.Service;

import java.sql.Connection;
import java.util.List;

import com.lms.DAO.PublisherDAO;
import com.lms.POJO.Publisher;

public class PublisherService {
	PublisherDAO pubDAO = new PublisherDAO();
	
	public void createPub(Connection con, int pubId, String pubName, String pubAddress, String pubPhone) {
		pubDAO.writeInsertPub(con, pubId, pubName, pubAddress, pubPhone);
		
		System.out.println("\nPublisher created successfully.");
	}
	
	public void updatePub(Connection con, int pubId, String pubName, String pubAddress, String pubPhone) {
		pubDAO.writeUpdatePub(con, pubId, pubName, pubAddress, pubPhone);
		
		System.out.println("\nPublisher updated successfully.");
	}
	
	public void deletePub(Connection con, int pubId) {
		pubDAO.writeDeletePub(con, pubId);
		
		System.out.println("\nPublisher deleted successfully.");
	}
	
	public void viewPub(Connection con) {
		pubDAO.readViewPub(con);
	}
	
	public String getData(Connection con, int id, String fieldName) {
		return pubDAO.getPubData(con, id, fieldName);
	}
	
	public boolean ifExists(Connection con, int pubId, boolean checkId) {
		List<Publisher> list = pubDAO.readPub(con);
		
		for(Publisher i : list) {
			if(i.getPublisherId() == pubId) {
				return checkId = false;
			}
		}
		return checkId = true;
	}
	
	public boolean ifNotExists(Connection con, int pubId, boolean checkId) {
		List<Publisher> list = pubDAO.readPub(con);
		
		for(Publisher i : list) {
			if(i.getPublisherId() == pubId) {
				return checkId = true;
			}
		}
		return checkId = false;
	}
}
