package org.bhopal.student;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
public class StudentPost extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String id=req.getParameter("id");
	int sid=Integer.parseInt(id);
	String sname=req.getParameter("sn");
	String sbranch=req.getParameter("br");
	String perc=req.getParameter("pr");
	Double sperc=Double.parseDouble(perc);
	PrintWriter out=resp.getWriter();
	out.println("<html><body bgcolor='585757'> <b> "
			+sname +"</b> of branch<b> "
					+sbranch+ "</b> your data is entered in database.<br> "
					     + "<a href='Student.html'>enter</a></body></html>");
	
	Connection con=null;
	PreparedStatement pstmt=null;
	String qry="insert into bhopal.student values(?,?,?,?)";
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=Bhavuk&password=1729");
		pstmt=con.prepareStatement(qry);
		pstmt.setInt(1, sid);
		pstmt.setString(2, sname);
		pstmt.setString(3, sbranch);
		pstmt.setDouble(4, sperc);
		pstmt.executeUpdate();
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
	finally {
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
}
