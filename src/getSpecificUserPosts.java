

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


@WebServlet("/getSpecificUserPosts")
public class getSpecificUserPosts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		ArrayList<post> postsList = new ArrayList<post>();
		try {	
			conn = DriverManager.getConnection(Utils.connecter);
			String query = "SELECT * from Posts WHERE UserID = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, UserID);
			rs = ps.executeQuery();		
			if(rs.next()) {
				do {
					postsList.add(new post(rs.getInt("PostID"), rs.getString("postText"), rs.getString("time_stamp"),
					rs.getInt("likes"), rs.getInt("shares"), rs.getInt("security_status")));
				}while(rs.next());
				String json = new Gson().toJson(postsList);
				response.setContentType("application/json");
				out.println(json);
			}else {
				out.println("No posts");
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
	class post{
		public int PostID;
		public String postText;
		public String date;
		public int likes;
		public int shares;
		public int security;
		public post(int PostID, String postText, String date, int likes, int shares, int security) {
			this.PostID = PostID;
			this.postText = postText;
			this.date = date;
			this.likes = likes;
			this.shares = shares;
			this.security = security;
		}
	}
}
