//MySqlAss3   

package com.rt.assignment;  //to delete the record from student2 db by using giving inputs

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteMysql {

	public static void main(String[] args) {
	Scanner sc = null;
	Connection con = null;
	Statement st = null;
	
		try {//read the input
					sc = new Scanner(System.in);
					String name= null;
					if(sc!=null)
					{
						System.out.println("Enter the Name for delete record::");
						name=sc.next();//gives pooja
					}//if
					//conver input as required SQL query
					name= "'"+name+"'";
			
				//Load the Driver Class
					//Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Establish the Connection
			con=DriverManager.getConnection("jdbc:mysql:///Rushi","root","root");
			//Create Statement Object
			if(con!=null)
				st = con.createStatement();
			
			//Prepare Sql query
			//delete from student2 where sname=pooja;
			String query = "DELETE FROM STUDENT2  WHERE SNAME="+name;
			System.out.println(query);
			//send and Execute query
			int count = 0;
			if(st!=null)
				count= st.executeUpdate(query);
			if(count==0)
				System.out.println("Record not Deleted");
			else
				System.out.println("Record Deleted");
		}//try
		catch(SQLException se)
		{
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("invalid col name or table name or sql syntax");
			if(se.getErrorCode()==1)
				System.out.println("Duplicates not allowed in primary key table:");
			else if(se.getErrorCode()==1400)
				System.out.println("Null cannot be inserted in primary key vaue:");
			se.printStackTrace();
		}//catch
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			try {
				if(st!=null)
					st.close();
			}//try
			catch(SQLException se)
			{
				se.printStackTrace();
			}//catch
			
			try {
				if(con!=null)
					con.close();
			}//try
			catch(SQLException se)
			{
				se.printStackTrace();
			}//catch

			try {
				if(sc!=null)
					sc.close();
			}//try
			catch(Exception e)
			{
				e.printStackTrace();
			}//catch
		}

	}
}