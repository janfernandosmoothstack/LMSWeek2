package com.lms.DAO;

import java.sql.*;

import com.lms.Presentation.BorrowerUserMenu;

public class BorrowerDAO {
       public static void main(String[] args) {
       try
       {
               Class.forName("com.mysql.jdbc.Driver");
              
               Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","TapHouse123");
               BorrowerUserMenu borrower = new BorrowerUserMenu();
               borrower.readCardNo(con);
              
        }
                   catch(Exception e)
                {
                    System.out.println(e);
                }
       }
}