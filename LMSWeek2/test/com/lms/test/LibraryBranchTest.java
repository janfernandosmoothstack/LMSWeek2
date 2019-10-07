package com.lms.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import com.lms.DAO.DaoLibrarian;

class LibraryBranchTest {

	DaoLibrarian lm = new DaoLibrarian();
    Connection con;
    
    @BeforeEach
    public void setUp() throws SQLException
    {
     con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Brownlenovo5!");
    }
    
    @Test
    public void checkNumberOfBranches() throws SQLException
    {
            PreparedStatement ps =  con.prepareStatement("SELECT count(branchId) FROM tbl_library_branch");
            ResultSet rs = ps.executeQuery();
            rs.next();
                assertEquals(5, rs.getInt(1), "Number of branches");
    }
    
    @Test
    public void checkNumberOfColumns() throws SQLException {
        PreparedStatement ps =  con.prepareStatement("SELECT * FROM tbl_library_branch");
        ResultSet rs = ps.executeQuery();
        java.sql.ResultSetMetaData rsmd = rs.getMetaData();
       int columnsNumber = rsmd.getColumnCount();
            assertEquals(3, columnsNumber, "Number of columns");
    }
    
    @Test
    public void checkUpdateBranchName() throws SQLException {
        String brName = "Fairfax";
        int brId = 4;
        lm.UpdateBranchName( con, brName,  brId);
        PreparedStatement ps = con.prepareStatement("SELECT branchName FROM tbl_library_branch WHERE branchId = 4");
        ResultSet rs = ps.executeQuery();
        rs.next();        
            assertEquals("Fairfax", rs.getString(1), "Number of columns");
    }

}
