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


@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("resource")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String securityQuestion = request.getParameter("securityQuestion");
		String securityAnswer = request.getParameter("securityAnswer");
		int UserID = 0;
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
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		try {	
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/FinalProject?user=root&password=root");
			String query = "SELECT * from Users WHERE username = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println("Username Exists.");
				response.setContentType("text/plain");
				out.print("Username Exists");
				return;
			}
			
			query = "INSERT into Users (username, password_, firstname, lastname, joined_on, date_of_birth, public_status)" +
							" values (?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, firstname);
			ps.setString(4, lastname);
			ps.setString(5, date);
			ps.setString(6, "DOB");
			ps.setInt(7, 1);
			ps.execute();		
			rs = ps.getGeneratedKeys();
			if(rs.next()){
				UserID=rs.getInt(1);
			}
			query = "INSERT into Security (UserID, SecurityQuestion, CustomAnswer)" +
					" values (?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setInt(1, UserID);
			ps.setString(2, securityQuestion);
			ps.setString(3, securityAnswer);
			ps.execute();
			
			response.setContentType("application/json");
			out.println("{");
			out.println("\"UserID\":" + UserID + ",");
			out.println("\"username\":" + "\"" + username + "\",");
			out.println("\"firstname\":" + "\"" + firstname + "\",");
			out.println("\"lastname\":" + "\"" + lastname + "\"");
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
