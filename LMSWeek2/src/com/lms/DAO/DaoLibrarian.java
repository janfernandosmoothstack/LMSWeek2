package com.lms.DAO;

import java.sql.*;

//import com.mysql.jdbc.*;


public class DaoLibrarian {
	//test
}
       public static void main(String[] args) {
       try
       {
               Class.forName("com.mysql.jdbc.Driver");
               
               Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Brownlenovo5!");
       
               String sql = "select * from tbl_library_branch";
               
                   
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
            
            while (rs.next())
            {
                //Print one row
                for(int i = 1 ; i <= columnsNumber; i++)
                {
                      System.out.print(rs.getString(i) + "\t "); //Print one element of a row
                }
                  System.out.println();//Move to the next line to print the next row.
            }
                           con.close();
        }
                   catch(Exception e)
                {
                    System.out.println(e);
                }
       }
