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

import com.lms.DAO.AuthorDAO;

class AuthorDAOTest {
	AuthorDAO authDAO = new AuthorDAO();
	Connection con;
	PreparedStatement ps;
	
	@BeforeEach
	public void setUp() throws SQLException {
     con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Iamsherlocked#2.0");
    }
	
	@Test
	public void checkWriteInsertAuthor() throws SQLException {
		authDAO.writeInsertAuthor(con, 8, "Test1");
		
		ps = con.prepareStatement("SELECT authorId, authorName FROM tbl_author WHERE authorId = 8");
        ResultSet rs = ps.executeQuery();
        rs.next();        
        
        assertEquals(8, rs.getInt(1), "AuthorId inserted");
        assertEquals("Test1", rs.getString(2), "Author Name Inserted");
	}
	
	@AfterEach
	public void tearDown() throws SQLException {
		ps.close();
		con.close();
	}

}
