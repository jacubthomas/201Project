

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class getBio
 */
@WebServlet("/getBio")
public class getBio extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		int UserID = Integer.parseInt(request.getParameter("UserID"));
		PrintWriter out = response.getWriter();
		try {
		    Class.forName(Utils.driver);
		} 
		catch (ClassNotFoundException e) {
		    e.printStackTrace();
		} 
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {	
			conn = DriverManager.getConnection(Utils.connecter);
			String query = "SELECT bio from Users WHERE UserID = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, UserID);
			rs = ps.executeQuery();		
			if(rs.next()) {
				out.println(rs.getString("bio"));
			}else {
				out.println("No bio");
			}
		
			
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
