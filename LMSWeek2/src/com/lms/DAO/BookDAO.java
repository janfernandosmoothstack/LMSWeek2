package com.lms.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.POJO.Author;
import com.lms.POJO.Book;
import com.lms.POJO.Publisher;

public class BookDAO extends DAO{
	public void writeInsertBook(Connection con, int bookId, String title, int authId, int pubId) {
		PreparedStatement ps = null;
		
		try {
			String sql = "INSERT INTO tbl_book "
							+ "VALUES (?, ?, ?, ?)";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, bookId);
			ps.setString(2, title);
			ps.setInt(3, authId);
			ps.setInt(4, pubId);
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
	
	public void writeUpdateBook(Connection con, int bookId, String newData, String fieldName) {
		super.updateString(con, bookId, newData, fieldName, "bookId", "tbl_book");
	}
	
	public void writeDeleteBook(Connection con, int bookId) {
		super.delete(con, bookId, "bookId", "tbl_book");
	}
	
	public void readViewBook(Connection con) {
		try {
			String sql = "SELECT * FROM tbl_book";
	        
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int columnsNumber = rsmd.getColumnCount();
	        
	        System.out.println("ID\t\tTitle\t\tAuthID\t\tPubID");
	    
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
	
	public List<Book> readBook(Connection con) {
		List<Book> bookList = null;
		
		try {
			Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT bookId, title, authId, pubId FROM tbl_publisher");         

        	bookList = new ArrayList<Book>();
        	
            while (rs.next()) {
            	Book book = new Book();
            	book.setBookId(rs.getInt("bookId"));
            	book.setTitle(rs.getString("title"));
            	
            	Author author = new Author();
            	author.setAuthorId(rs.getInt("authorId"));
            	book.setAuthor(author);
            	
            	Publisher pub = new Publisher();
            	pub.setPublisherId(rs.getInt("publisherId"));
            	book.setPublisher(pub);
            	
            	bookList.add(book);
            }
        } catch (SQLException e) {
        	System.out.println(e);
        }
		
		return bookList;
	}
}
