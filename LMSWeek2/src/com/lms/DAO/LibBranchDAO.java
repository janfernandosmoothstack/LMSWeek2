package com.lms.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.POJO.LibraryBranch;

public class LibBranchDAO extends DAO {
	public void writeInsertLibBran(Connection con, int branchId, String branchName, String branchAddress) {
		PreparedStatement ps = null;
		
		try {
			String sql = "INSERT INTO tbl_library_branch "
							+ "VALUES (?, ?, ?)";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, branchId);
			ps.setString(2, branchName);
			ps.setString(3, branchAddress);
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
	
	public void writeUpdateLibBran(Connection con, int branId, String branName, String branAddress) {
		PreparedStatement ps = null;
		
		try {
			String sql = "UPDATE tbl_library_branch"
							+ " SET branchName = ?, branchAddress = ? "
							+ "WHERE branchId = ?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, branName);
			ps.setString(2, branAddress);
			ps.setInt(3, branId);
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
	
	public void writeDeleteLibBran(Connection con, int libBranId) {
		super.delete(con, libBranId, "branchId", "tbl_library_branch");
	}
	
	//Get data for field that does not need to be updated
	public String getBranData(Connection con, int id, String fieldName) {
		return super.getStringData(con, id, "branchId", fieldName, "tbl_library_branch");
	}
	
	public void dispatchBooks(Connection con, int libBranId, int newBranId) {
		PreparedStatement ps = null;
		
		try {
			String sql = "UPDATE tbl_book_copies"
							+ " SET branchId = ? "
							+ "WHERE branchId = ?";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, newBranId);
			ps.setInt(2, libBranId);
			ps.executeUpdate();
			ps.close();
			
			sql = "UPDATE tbl_book_loans"
					+ " SET branchId = ? "
					+ "WHERE branchId = ?";
	
			ps = con.prepareStatement(sql);
			ps.setInt(1, newBranId);
			ps.setInt(2, libBranId);
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
	
	public void readDispatchBran(Connection con, int libBranId) {
		try {
			String sql = "SELECT * FROM tbl_library_branch "
					+ "WHERE branchId <> " + libBranId;
	        
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int columnsNumber = rsmd.getColumnCount();
	        
	        System.out.println("ID\t\tName\t\t\tAddress");
	    
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
	
	public void readViewLibBran(Connection con) {
		try {
			String sql = "SELECT * FROM tbl_library_branch";
	        
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int columnsNumber = rsmd.getColumnCount();
	        
	        System.out.println("ID\t\tName\t\tAddress");
	    
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
	
	public List<LibraryBranch> readLibBran(Connection con) {
		List<LibraryBranch> libBranList = null;
		
		try {
			Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT branchId, branchName, branchAddress FROM tbl_library_branch");         

            libBranList = new ArrayList<LibraryBranch>();
        	
            while (rs.next()) {
            	LibraryBranch branch = new LibraryBranch();
            	
            	branch.setBranchId(rs.getInt("branchId"));
            	branch.setBranchName(rs.getString("branchName"));
            	branch.setBranchAddress(rs.getString("branchAddress"));
                libBranList.add(branch);
            }
        } catch (SQLException e) {
        	System.out.println(e);
        }
		
		return libBranList;
	}
}
