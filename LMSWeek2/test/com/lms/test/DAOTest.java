package com.lms.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lms.DAO.DAO;

class DAOTest {
	DAO dao = new DAO();
	Connection con;
	PreparedStatement ps;
	
	@BeforeEach
	public void setUp() throws SQLException {
     con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Iamsherlocked#2.0");
    }
	
	/*@Test
	public void checkUpdateString() throws SQLException {
		dao.updateString(con, 1, "Test2", "authorName", "authorId", "tbl_author");
		
		ps = con.prepareStatement("SELECT authorName FROM tbl_author WHERE authorId = 1");
        ResultSet rs = ps.executeQuery();
        rs.next();        
        
        assertEquals("Test2", rs.getString(2), "Author Name Updated");
	}
	
	/*@Test
	public void checkUpdateInt() throws SQLException {
		dao.updateInt(con, 1, 2, "pubId", "bookId", "tbl_book");
		
		ps = con.prepareStatement("SELECT pubId FROM tbl_book WHERE bookId = 1");
        ResultSet rs = ps.executeQuery();
        rs.next();        
        
        assertEquals(2, rs.getInt(1), "Pub Id Updated");
	}
	
	@Test
	public void checkDelete() throws SQLException {
		dao.delete(con, 1, "authorId", "tbl_author");
		
		ps = con.prepareStatement("SELECT authorName FROM tbl_author WHERE authorId = 1");
        ResultSet rs = ps.executeQuery();
        rs.next();        
        
        assertEquals("Test2", rs.getString(2), "Author Name Updated");
	}*/
	
	@AfterEach
	public void tearDown() throws SQLException {
		ps.close();
		con.close();
	}

}
