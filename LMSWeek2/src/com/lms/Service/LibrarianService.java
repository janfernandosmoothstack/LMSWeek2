package com.lms.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.lms.Presentation.MenuInterface;

public class LibrarianService {

	//Changing number of copies for BookID in the branch
	public void addCopies(Connection con, int bookId, String copies, int brId, String brName)
	{
		System.out.println("\nExisting number of copies: "+copies);
		System.out.println("Enter new number of copies: ");
		int newCopy = MenuInterface.readInt();

		try 
		{
			PreparedStatement ps =  con.prepareStatement("Update tbl_book_copies SET noOfCopies = ? WHERE bookId = ?");
			ps.setInt(1, newCopy);
			ps.setInt(2, bookId);
			ps.executeUpdate();

			System.out.println("\nSuccessfully Updated");
			return;

		} 
		catch (SQLException e) 
		{
			System.out.println(e);
		}
	}

	//Updating only Branch Address
	public void UpdateOnlyBranchAddress(Connection con, int brId, String brName, String newName) 
	{
		System.out.println("Please enter new branch address or enter N/A for no change:");
		newName = MenuInterface.readString();

		if(newName.equalsIgnoreCase("quit"))
		{
			return;
		}

		if(newName.equalsIgnoreCase("N/A") && newName.equalsIgnoreCase("N/A"))
		{
			System.out.println("Update canceled, please try again! Enter 'quit at any time to return \n");
			return;
		}

		try 
		{
			PreparedStatement ps =  con.prepareStatement("Update tbl_library_branch SET branchAddress = ? WHERE branchId = ?");
			ps.setString(1, newName);
			ps.setInt(2, brId);
			ps.executeUpdate();

			System.out.println("\nSuccessfully Updated the 'Branch Address' only");
			return;

		} 
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}	

	//Update only branch Name 
	public void UpdateOnlyBranchName(Connection con, int brId, String brName, String newName)
	{
		try
		{
			PreparedStatement ps =  con.prepareStatement("Update tbl_library_branch SET branchName = ? WHERE branchId = ?");
			ps.setString(1, newName);
			ps.setInt(2, brId);
			ps.executeUpdate();

			System.out.println("\nSuccessfully Updated the 'Branch Name' only");
			return;

		} 
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}


}