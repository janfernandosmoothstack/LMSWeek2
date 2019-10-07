package com.lms.DAO;

import java.sql.*;

public class DaoLibrarian 
{
	public void UpdateBranchName(Connection con, String brName, int brId) throws SQLException {
        PreparedStatement ps =  con.prepareStatement("UPDATE tbl_library_branch SET branchName =  ?"
                + " WHERE branchId = ?");
        ps.setString(1, brName);
        ps.setInt(2, brId);
        ps.executeUpdate();
    }
}
