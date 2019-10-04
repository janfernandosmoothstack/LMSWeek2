package com.lms.DAO;



import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

//import com.mysql.jdbc.Connection;

public class DAO {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
           
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Iamsherlocked#2.0");
   
            String sql = "select * from tbl_library_branch";
           
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
        
	        while (rs.next()) {
	            //Print one row
	            for(int i = 1 ; i <= columnsNumber; i++) {
	            	System.out.print(rs.getString(i) + "\t "); //Print one element of a row
	            }
	              	
	            System.out.println();//Move to the next line to print the next row.
        	}
            
	        con.close();
		} catch(Exception e) {
			System.out.println(e);
        }
   }
}
