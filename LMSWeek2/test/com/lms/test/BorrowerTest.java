package com.lms.test;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lms.DAO.LoansDAO;


class BorrowerTest {
	Connection con;
	LoansDAO rename = new LoansDAO();
    
    @BeforeEach
    public void setUp() throws SQLException
    {
     con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","TapHouse123");
    }
    
    @Test
    public  void getAllShouldReturn4Loans() throws SQLException {
        
        try
        {
            PreparedStatement ps =  con.prepareStatement("SELECT count(branchId) FROM tbl_book_loans");
            ResultSet rs = ps.executeQuery();
            rs.next();
                assertEquals(4, rs.getInt(1), "Works!");
            
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
    
    @Test
    public  void getnoOfCopies() throws SQLException {
        
        try
        {
            PreparedStatement ps =  con.prepareStatement("SELECT noOfCopies FROM tbl_book_copies WHERE bookId =1 AND branchId =1");
            ResultSet rs = ps.executeQuery();
            rs.next();
                assertEquals(6, rs.getInt(1), "Works!");
            
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
    
    @Test
    public  void getUpdateBorrowerName() throws SQLException {
            String brAddress = "Euless";
            int brId = 1;
            rename.UpdateBorrowerName( con,  brId,  brAddress);
            PreparedStatement ps = con.prepareStatement("SELECT branchAddress FROM tbl_library_branch WHERE branchId = 1");
            ResultSet rs = ps.executeQuery();
            rs.next();        
                assertEquals("Euless", rs.getString(1), "Number of columns");
        }
    
}
