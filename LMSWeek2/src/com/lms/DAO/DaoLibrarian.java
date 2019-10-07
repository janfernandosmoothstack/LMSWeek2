package com.lms.DAO;

import java.sql.*;
import com.lms.Presentation.LibrarianMenu;

public class DaoLibrarian 
{
	
   public static void main(String[] args) {
   Connection con = null;
   try
   {	   		
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Brownlenovo5!");
              
       LibrarianMenu lib = new LibrarianMenu();
       lib.showMenu(con);
               
    }
    catch(Exception e)
    {
        System.out.println(e);
     }
    finally 
    {
    	try 
    	{
			con.close();
		} 
    	catch (SQLException e)
    	{
			System.out.println(e);
		}
       }
      }
}
