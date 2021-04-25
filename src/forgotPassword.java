

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

import com.google.gson.Gson;


@WebServlet("/forgotPassword")
public class forgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		String username = request.getParameter("username");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
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
			String query = "SELECT * from Users u, Security s WHERE u.UserID=s.UserID AND u.username = ? AND s.SecurityQuestion = ?"
					+ "AND s.CustomAnswer = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, question);
			ps.setString(3, answer);
			rs = ps.executeQuery();	
			Status status = null;
			if(rs.next()) {
				status = new Status(true, rs.getInt("UserID"));
			}else {
				status = new Status(false, rs.getInt("UserID"));
			}
			String json = new Gson().toJson(status);
			out.println(json);
		
			
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
	class Status{
		public boolean success;
		public int UserID;
		public Status(boolean success, int UserID) {
			this.success = success;
			this.UserID = UserID;
		}
	}
}
