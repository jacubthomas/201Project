

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/post")
public class post extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int UserID = Integer.parseInt(request.getParameter("UserID"));
		String Input = request.getParameter("Input");
		String Security = request.getParameter("Security");
		String Recipient = request.getParameter("Recipient");
		
		PrintWriter out = response.getWriter();
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
		    e.printStackTrace();
		} 
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String date = new SimpleDateFormat("dd-M-yyyy hh:mm:ss").format(new Date());
		try {	
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/FinalProject?user=root&password=root");
			String query = "INSERT into Posts (UserID, postText, security_status, time_stamp, likes, shares)" +
					" values (?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setInt(1, UserID);
			ps.setString(2, Input);
			if(Security.equalsIgnoreCase("false"))
				ps.setInt(3, 1);
			else {
				if(Recipient != "")
					ps.setInt(3, 3);
				else
					ps.setInt(3,2);
			}
			ps.setString(4, date);
			ps.setInt(5, 0);
			ps.setInt(6, 0);
			ps.execute();		
			out.println("post success!");
			System.out.println("post success!");		
			
		}catch(SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
	}

}
