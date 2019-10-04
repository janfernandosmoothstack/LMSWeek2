
import java.sql.*;

public class Dao {

	   public static void main(String[] args) {
	       // TODO Auto-generated method stub
	       try
	       {
	           Class.forName("com.mysql.jdbc.Driver");
	           
	           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Brownlenovo5!");
	          
	           
	           //Statement stmt = con.createStatement();
          String sql = "select * from tbl_library_branch";
//	           Statement stmt = con.prepareStatement(sql);
	           



Statement st = con.createStatement();
ResultSet rs = st.executeQuery(sql);
ResultSetMetaData rsmd = rs.getMetaData();
int columnsNumber = rsmd.getColumnCount();
	           
	         //  ResultSet rs = stmt.executeQuery(sql);
	           
//	           while(rs.next())
//	           {
//	        	   //String name = stmt.getString(2);
//	        	   System.out.println(rs.getString(2));
//	           }

while (rs.next()) {
	//Print one row          
	for(int i = 1 ; i <= columnsNumber; i++){

	      System.out.print(rs.getString(i) + " "); //Print one element of a row

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
	
}
