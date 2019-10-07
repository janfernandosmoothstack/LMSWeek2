package com.lms.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAO {
	public void updateString(Connection con, int id, String newData, String fieldName, String idFieldName, String tblName) {
		PreparedStatement ps = null;
		
		try {
			String sql = "UPDATE " + tblName
							+ " SET " + fieldName + " = ? "
							+ "WHERE " + idFieldName + " = ?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, newData);
			ps.setInt(2, id);
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}
	
	public void updateInt(Connection con, int id, int newData, String fieldName, String idFieldName, String tblName) {
		PreparedStatement ps = null;
		
		try {
			String sql = "UPDATE " + tblName
					+ " SET " + fieldName + " = ? "
					+ "WHERE " + idFieldName + " = ?";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, newData);
			ps.setInt(2, id);
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}
	
	public void delete(Connection con, int id, String idFieldName, String tblName) {
		PreparedStatement ps = null;
		
		try {
			String sql = "DELETE FROM " + tblName
							+ " WHERE " + idFieldName + " = ?";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}
}
