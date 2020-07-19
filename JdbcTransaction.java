package org.bhopal.jdbcApp;

import java.sql.*;
import java.util.*;
public class JdbcTransaction {
public static void main(String[] args) {
	Connection con=null;
	PreparedStatement pstmt1=null;
	PreparedStatement pstmt2=null;
	Savepoint sp1=null;
	String qry1="insert into bhopal.student1 values(?,?,?,?)";
	String qry2="insert into bhopal.student2 values(?,?,?)";
	Scanner sc=new Scanner(System.in);
	System.out.println("enter your id=");
	int id=sc.nextInt();
	System.out.println("enter your name=");
	String name=sc.next();
	System.out.println("enter your department name=");
	String dept=sc.next();
	System.out.println("enter your percentage=");
	Double perc=sc.nextDouble();
	System.out.println("enter your place=");
	String place=sc.next();
	sc.close();
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=Bhavuk&password=1729");
		con.setAutoCommit(false);
		pstmt1=con.prepareStatement(qry1);
		pstmt1.setInt(1,id);
		pstmt1.setString(2, name);
		pstmt1.setString(3, dept);
		pstmt1.setDouble(4, perc);
		pstmt1.executeUpdate();
		sp1=con.setSavepoint();
		System.out.println("student1 record entered successfully");
		
		pstmt2=con.prepareStatement(qry2);
		pstmt2.setInt(1, id);
		pstmt2.setString(2,name);
		pstmt2.setString(3,place);
		pstmt2.executeUpdate();
		con.commit();
		System.out.println("student2 record entered successfully");
		
	} catch (ClassNotFoundException | SQLException e) {
		try {
			if(sp1!=null) {
				con.rollback(sp1);
				con.commit();			
			}
			else {
				con.rollback();
				System.out.println("rolled back");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		e.printStackTrace();
	}
	finally {
		if(pstmt1!=null) {
			try {
				pstmt1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(sp1!=null) {
		try {
			con.releaseSavepoint(sp1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		
		if(pstmt2!=null) {
			try {
				pstmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
}
