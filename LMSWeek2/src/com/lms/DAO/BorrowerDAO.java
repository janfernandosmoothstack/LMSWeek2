package com.lms.DAO;

import java.sql.*;

import com.lms.Presentation.BorrowerUserMenu;
import com.mysql.jdbc.ResultSetMetaData;

public class BorrowerDAO {
       public static void main(String[] args) {
       try
       {
               Class.forName("com.mysql.jdbc.Driver");
              
               Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","TapHouse123");
             
               String sql = "select * from tbl_borrower";
              
                 //executeQuesry for select and executeUpdate for insert, delete, update  or any change
               
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                //just for displaying 
                java.sql.ResultSetMetaData rsmd = rs.getMetaData();
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
                           con.close(); //close connection
        }
                   catch(Exception e)
                {
                    System.out.println(e);
                }
       }
}