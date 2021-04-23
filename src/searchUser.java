

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

@WebServlet("/searchUser")
public class searchUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		String username = request.getParameter("username");
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
		PrintWriter out = response.getWriter();
		try {	
			conn = DriverManager.getConnection(Utils.connecter);
			st = conn.createStatement();
			String query = "SELECT UserID, bio from Users WHERE username = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			Result res = null;
			if(rs.next()) {
				res = new Result(rs.getInt("UserID"), rs.getString("bio"), "Success");
			}else {
				res = new Result(0, "", "Failed");
			}
			String json = new Gson().toJson(res);
			response.setContentType("application/json");
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
	class Result{
		public int UserID;
		public String bio;
		public String status;
		public Result(int UserID, String bio, String status) {
			this.UserID = UserID;
			this.bio = bio;
			this.status = status;
		}
	}

}
