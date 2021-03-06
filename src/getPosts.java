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

@WebServlet("/getPosts")
public class getPosts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	class post{
		public int UserID;
		public String username;
		public int PostID;
		public String postText;
		public String date;
		public int likes;
		public int shares;
		public int security;
		public post(int UserID, String username, int PostID, String postText, String date, int likes, int shares, int security) {
			this.UserID = UserID;
			this.username = username;
			this.PostID = PostID;
			this.postText = postText;
			this.date = date;
			this.likes = likes;
			this.shares = shares;
			this.security = security;
		}
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
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
		ArrayList<post> postsList = new ArrayList<post>();
		try {	
			conn = DriverManager.getConnection(Utils.connecter);
			st = conn.createStatement();
			rs = st.executeQuery("SELECT u.UserID, p.PostId, u.username, p.postText, p.time_stamp, p.likes, p.shares, p.security_status"
					+ " from Users u, Posts p " 
					+ "WHERE u.UserID=p.UserID ORDER BY p.PostID DESC");
			if(rs.next()) {
				do {
					postsList.add(new post(rs.getInt("UserID"), rs.getString("username"), rs.getInt("PostID"),
							rs.getString("postText"), rs.getString("time_stamp"),rs.getInt("likes"), rs.getInt("shares"), rs.getInt("security_status")));
				}while(rs.next());
				String json = new Gson().toJson(postsList);
				response.setContentType("application/json");
				out.println(json);
			}else {
				out.println("No Posts");
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
