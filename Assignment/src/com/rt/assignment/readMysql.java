package com.rt.assignment;  //to get the records from the student2 table by using given details

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class readMysql {

	public static void main(String[] args) {
	Scanner sc = null;
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	
		try {//read the input
					sc = new Scanner(System.in);
					String name1 = null;
					String name2 = null;
					String name3 = null;
					if(sc!=null)
					{
						System.out.println("Enter the first Student Name::");
						name1=sc.next();//gives pooja
						System.out.println("Enter the Second Student Name::");
						name2=sc.next();//gives sonal
						System.out.println("Enter the Third Student Name::");
						name3=sc.next();//gives kajal
					}//if
					//conver input as required SQL query
					name1= "'"+name1+"'";    //gives 'pooja'
					name2= "'"+name2+"'";    //gives 'sonal'
					name3= "'"+name3+"'";    //gives 'kajal'
					
				//Load the Driver Class
					//Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Establish the Connection
			con=DriverManager.getConnection("jdbc:mysql:///Rushi","root","root");
			//Create Statement Object
			if(con!=null)
				st = con.createStatement();
			
			//Prepare Sql query
			//select sno,sname,sadd,avg from student where sname in('pooja','sonal','kajal');
			String query = "SELECT * FROM STUDENT2";
			System.out.println(query);
			//send and Execute query using Resultset Obj
			if(st!=null)
				rs = st.executeQuery(query);
			
			if(rs!=null){
				// process the ResulsET REsult
			boolean flag = false;
			while(rs.next()!=false)
			{  flag = true;
				System.out.println(rs.getInt(1)+"  "+rs.getString(2) +"  "+rs.getDate(3)+"  "+rs.getDate(4));
			}//while
			if(flag==false)
				System.out.println("NO RECORDS FOUND");
			}//if	
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
				if(rs!=null)
					rs.close();
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