package com.rt.assignment;//toUpdate the details in emp db by giving  records from mysql db

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class UpdateMysql {

	public static void main(String[] args) {
		Scanner sc =null;
		Connection con = null;
		Statement st = null;
		
		try
		{//read input
			sc = new Scanner(System.in);
			int no=0;
			String name = null;
         
			if(sc!=null)
			{
				System.out.println("Enter the Student  No::");
				no = sc.nextInt(); //gives 102
				System.out.println("Enter the Student New Name::");
				name= sc.next(); //givesANALYST
	
				name="'"+name+"'";//gives 'pooja'
			//load the jdbc driver
			
			// Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the connection
			con = DriverManager.getConnection("jdbc:mysql:///Rushi","root","root");
			//create Statement Object
			if(con!=null)
				st =con.createStatement();
			//prapare sql query
			//update student2 set sname = pooja  where sno=102;
			String query = "UPDATE STUDENT2 SET SNAME="+name+" WHERE SNO="+no;
			System.out.println(query);
			//execute query
			int count =0;
			if(st!=null)
				count=st.executeUpdate(query);
			if(count==0)
				System.out.println("Record Not Inserted:::");
			else
				System.out.println("Record Inserted:::");
			}
		}//try
		catch(SQLException se)
		{
			if(se.getErrorCode()==1)
				System.out.println("Duplicates not allowed in primary key table:");
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("invalid col naem or table name or sql keyword:");
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
				if(st!=null)
				 st.close();
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
