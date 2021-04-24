

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


@WebServlet("/getUsers")
public class getUsers extends HttpServlet {
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
		ArrayList<User> usersList = new ArrayList<User>();
		try {	
			conn = DriverManager.getConnection(Utils.connecter);
			st = conn.createStatement();
			String query = "SELECT * from Users WHERE username != ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()) {
				do {
					usersList.add(new User(rs.getInt("UserID"), rs.getString("username"), rs.getString("bio")));
				}while(rs.next());
				String json = new Gson().toJson(usersList);
				out.println(json);
			}else {
				out.println("No other Users");
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
	
	class User{
		public int UserID;
		public String username;
		public String bio;
		public User(int UserID, String username, String bio) {
			this.UserID = UserID;
			this.username = username;
			this.bio = bio;
		}
	}

}
