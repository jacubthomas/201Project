

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


@WebServlet("/changePassword")
public class changePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@SuppressWarnings("resource")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oldPass = request.getParameter("oldPassword");
		String newPass = request.getParameter("newPassword");
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
			String query = "SELECT password_ from Users WHERE UserID = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, UserID);
			rs = ps.executeQuery();		
			
			if(rs.next()) {
				//update password
				if(rs.getString("password_").equals(oldPass)) {
					query = "UPDATE Users set password_=? where UserID=?";
					ps = conn.prepareStatement(query);
					ps.setString(1, newPass);
					ps.setInt(2, UserID);
					ps.executeUpdate();
					out.println("Successfully changed Password");
				}else {
					out.println("Wrong Password");
				}
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
