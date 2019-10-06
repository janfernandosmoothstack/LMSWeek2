package com.lms.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.POJO.Borrower;

public class BorrowerDAO extends DAO {
	public void writeInsertBorr(Connection con, int cardNo, String name, String address, String phone) {
		PreparedStatement ps = null;
		
		try {
			String sql = "INSERT INTO tbl_borrower "
							+ "VALUES (?, ?, ?, ?)";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, cardNo);
			ps.setString(2, name);
			ps.setString(3, address);
			ps.setString(4, phone);
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
	
	public void writeUpdateBorr(Connection con, int cardNo, String newData, String fieldName) {
		super.updateString(con, cardNo, newData, fieldName, "cardNo", "tbl_borrower");
	}
	
	public void writeDeleteBorr(Connection con, int cardNo) {
		super.delete(con, cardNo, "cardNo", "tbl_borrower");
	}
	
	public void readViewBorr(Connection con) {
		try {
			String sql = "SELECT * FROM tbl_borrower";
	        
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int columnsNumber = rsmd.getColumnCount();
	        
	        System.out.println("ID\t\tName\t\tAddress\t\tPhone");
	    
	        while (rs.next()) {
	            //Print one row
	            for(int i = 1 ; i <= columnsNumber; i++) {
	            	System.out.print(rs.getString(i) + "\t\t"); //Print one element of a row
	            }
	              	
	            System.out.println();//Move to the next line to print the next row.
	    	}
	        
		} catch (SQLException e) {
			System.out.print(e);
		}
	}
	
	public List<Borrower> readBorr(Connection con) {
		List<Borrower> borrList = null;
		
		try {
			Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT cardNo, name, address, phone FROM tbl_borrower");         

            borrList = new ArrayList<Borrower>();
        	
            while (rs.next()) {
            	Borrower borr = new Borrower();
            	
            	borr.setCardNo(rs.getInt("cardNo"));
            	borr.setName(rs.getString("name"));
            	borr.setAddress(rs.getString("address"));
            	borr.setPhone(rs.getString("phone"));
            	borrList.add(borr);
            }
        } catch (SQLException e) {
        	System.out.println(e);
        }
		
		return borrList;
	}
}
