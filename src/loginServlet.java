import java.io.IOException;

/* Name: Miguel Sanchez
Course: CNT 4714 – Spring 2024 – Project Four
Assignment title: A Three-Tier Distributed Web-Based Application
Date: April 23, 2024
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/credentialsDB", "root", "rootMAC1$");
			String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        
	        if (isValidCredentials(con,username,password)) {
	        	String redirectPage = getRedirectPage(username);
	        	response.sendRedirect(redirectPage);
	        } else { response.sendRedirect("errorPage.html");
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private boolean isValidCredentials(Connection con, String username, String password) throws SQLException {
	    // Query the database to check if the provided username and password exist
	    PreparedStatement ps = con.prepareStatement("SELECT * FROM userCredentials WHERE login_username=? AND login_password=?");
	    ps.setString(1, username);
	    ps.setString(2, password);
	    ResultSet rs = ps.executeQuery();
	    return rs.next(); // Returns true if the query returns a result, indicating valid credentials
	}
	
	private String getRedirectPage(String username) {
		switch(username) {
		case "root":
			return "rootHome.jsp";
		case "client":
			return "clientHome.jsp";
		case "dataentryuser":
			return "dataEntryHome.jsp";
		case "theaccountant":
			return "accountantHome.jsp";
		default:
			return "errorPage.html";
		}
	}
	}
