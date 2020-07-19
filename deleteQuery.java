package org.bhopal.jdbcApp;
import java.sql.*;
public class deleteQuery {
	public static void main(String[] args) {
		Connection conn=null;
		Statement stmt=null;
		String deleteQuery="delete from bhopal.student where id=3";
      try {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("load and register successfully....");
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=Bhavuk&password=1729");
		System.out.println("server connected successfully....");
		stmt=conn.createStatement();
		System.out.println("platform created......");
		stmt.execute(deleteQuery);
		System.out.println("record deleted.....");
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
      finally {
    	  if(stmt!=null) {
    		  try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	  }
    	  if(conn!=null) {
    		  try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	  }
      }
}
}
