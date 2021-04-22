

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

@WebServlet("/settings")
public class settings extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("resource")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bio = request.getParameter("bio");
		String username = request.getParameter("username");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		int UserID = Integer.parseInt(request.getParameter("UserID"));
		String status = request.getParameter("status");
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
			String query = "";
			Updates updates = new Updates();
			query = "UPDATE Users set bio=? where UserID=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, bio);
			ps.setInt(2, UserID);
			ps.executeUpdate();
			updates.bio = bio;
			
			if(username.replaceAll("\\s+","").length() != 0) {
				query = "UPDATE Users set username=? where UserID=?";
				ps = conn.prepareStatement(query);
				ps.setString(1, username);
				ps.setInt(2, UserID);
				ps.executeUpdate();
				updates.username =  username;
			}
			if(fname.replaceAll("\\s+","").length() != 0) {
				query = "UPDATE Users set firstname=? where UserID=?";
				ps = conn.prepareStatement(query);
				ps.setString(1, fname);
				ps.setInt(2, UserID);
				ps.executeUpdate();
				updates.fname = fname;
			}
			if(lname.replaceAll("\\s+","").length() != 0) {
				query = "UPDATE Users set lastname=? where UserID=?";
				System.out.println(lname);
				ps = conn.prepareStatement(query);
				ps.setString(1, lname);
				ps.setInt(2, UserID);
				ps.executeUpdate();
				updates.lname = lname;
			}
			if(!status.equals("0")) {
				query = "UPDATE Users set public_status=? where UserID=?";
				ps = conn.prepareStatement(query);
				int public_status = status.equals("public") ? 1 : 2;
				ps.setInt(1, public_status);
				ps.setInt(2, UserID);
				ps.executeUpdate();
				updates.status = public_status;
			}
			String json = new Gson().toJson(updates);
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
	class Updates{
		public String bio = "";
		public String username = "";
		public String fname = "";
		public String lname = "";
		public int status = 0;
	}

}
