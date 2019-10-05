package com.lms.Presentation;
import java.sql.*;

public class LibrarianMenu implements MenuInterface {
	
	public void showMenu(Connection con) {
		String choice = "";
		
		do {
			String menu = "Pick an option\n\n" +
					"1. Enter the branch you manage\n" +
					"2. Exit\n\n" +
					"Please select an option(1-2):";
			
			System.out.println(menu);
			
			boolean checkChoice = false;
			
			//loop until user enters a valid choice
			while(checkChoice != true) {
				choice = MenuInterface.readString();
				
				switch(choice) {
					case "1":
						//Display branches
						Lib1(con);
						checkChoice = true;
						break;
					case "2":
						return;
					default:
						System.out.println("Please enter a valid option.");
						break;
				}
			}
		} while(choice != "4");
	}
	
	public static void Lib1(Connection con) {
		try {
			Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from tbl_library_branch");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            
            System.out.println("Branch ID \t Branch Name \t Branch Address");
            while (rs.next())
              {
                  //Print one row
                  for(int i = 1 ; i <= columnsNumber; i++)
                  {
                        System.out.print(rs.getString(i) + "\t\t "); //Print one element of a row
                  }
                    System.out.println();//Move to the next line to print the next row.
              }
                           	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				con.close();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
}