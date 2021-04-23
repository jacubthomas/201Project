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

@WebServlet("/like")
public class like extends HttpServlet {
	private static final long serialVersionUID = 4L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		int PostID = Integer.parseInt(request.getParameter("PostID"));
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
			String query = "SELECT likes from Posts WHERE PostID = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, PostID);
			rs = ps.executeQuery();		
			int likes = 0;
			if(rs.next()) {
				likes = rs.getInt("likes");
				likes++;
			} else {
				response.setContentType("text/plain");
				out.print("Invalid Login");
			}
			query = "UPDATE Posts SET likes = ? WHERE PostID = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, likes);
			ps.setInt(2, PostID);
			ps.execute();
			response.setContentType("application/json");
			out.println("{");
			out.println("\"Likes\":" + likes );
			out.println("}");
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
