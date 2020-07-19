package bhopal.jspider.jdbcApp;
import java.sql.*;
public class PrepareFetchNext{
public static void main(String[] args) {
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;	
	String query="select * from bhopal.student";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=Bhavuk&password=1729");
			pstmt=con.prepareStatement(query);
			rs=pstmt.executeQuery();
			while(rs.next()) {
			
				int id=rs.getInt(1);
				String name=rs.getString(2);
				double perc=rs.getDouble(3);
				System.out.println(id+" "+name+" "+perc);					
			}
					
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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

