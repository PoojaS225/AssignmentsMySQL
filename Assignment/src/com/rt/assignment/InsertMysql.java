package com.rt.assignment;//to insert the details in Student2 db by giving  records from mysql db 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Date;



public class InsertMysql {

	public static void main(String[] args) {
		Scanner sc =null;
		Connection con = null;
		Statement st = null;
		
		try
		{//read input
			sc = new Scanner(System.in);
			int no=0;
			String name = null;
			String dob = null;
			String doj = null;
         
			if(sc!=null)
			{
				System.out.println("Enter the Student No::");
				no = sc.nextInt(); //gives 101
				System.out.println("Enter the Student Name::");
				name = sc.next().toUpperCase();//gives rakesh
				System.out.println("Enter the Student DOB::");
				dob = sc.next();//gives DOB
				System.out.println("Enter the Student DOJ::");
				doj = sc.next(); //gives date of joining
			}
			
			//convert inpur as required for sql query
			name="'"+name+"'";  //gives 'Pooja'
			dob= "'"+dob+"'";  //gives 'dob'
			doj = "'"+doj+"'";
			
			//load the jdbc driver
			
			// Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the connection
			con = DriverManager.getConnection("jdbc:mysql:///Rushi","root","root");
			//create Statement Object
			if(con!=null)
				st =con.createStatement();
			//prapare sql query
			//insert into student2(sno,sname,sdob,sdoj) values (101,'pooja','1998-05-22','2020-02-07');
			String query = "INSERT INTO STUDENT2(SNO,SNAME,SDOB,SDOJ) VALUES ("+no+","+name+","+dob+","+doj+")";
			System.out.println(query);
			//execute query
			int count =0;
			if(st!=null)
				count=st.executeUpdate(query);
			if(count==0)
				System.out.println("Record Not Inserted:::");
			else
				System.out.println("Record Inserted:::");
			
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
		}//finally
	}//main

}//class
