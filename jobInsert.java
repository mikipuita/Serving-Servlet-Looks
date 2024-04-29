import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;


/**
 * Servlet implementation class rootServlet
 */
public class jobInsert extends HttpServlet {
	private Connection con;
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	 // Establish the database connection
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project4", "dataentryuser", "dataentryuser");

        // Retrieve form parameters
        String jnum = request.getParameter("jnum");
        String jname = request.getParameter("jname");
        String numworkers = request.getParameter("numworkers");
        String city = request.getParameter("cityyy");


        // Prepare and execute the update statement
        PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO jobs (jnum, jname, numworkers, city) VALUES (?, ?, ?, ?)");
        preparedStatement.setString(1, jnum);
        preparedStatement.setString(2, jname);
        preparedStatement.setString(3, numworkers);
        preparedStatement.setString(4, city);
        

        
        int rowsAffected = preparedStatement.executeUpdate();
        preparedStatement.close();
        
        // Set the response content type
        response.setContentType("text/html");

     // Construct the execution results message
        String executionResults;
        if (rowsAffected > 0) {
            executionResults = "<h2>New jobs record:</h2><p>(" + jnum + ", " + jname + ", " + numworkers + ", " +  city + ") - Successfully entered into database</p>";
        } else {
            executionResults = "<h2>Error:</h2><p>No rows affected. The record might not have been inserted successfully.</p>";
        }

       
        request.setAttribute("executionResults", executionResults);
        RequestDispatcher dispatcher = request.getRequestDispatcher("dataEntryHome.jsp");
        dispatcher.forward(request, response);
        
    } catch (SQLException e) {{
    	String errorMessage = "<h2>Error:</h2><p>" + e.getMessage() + "</p>";
        request.setAttribute("executionResults", errorMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("dataEntryHome.jsp");
        dispatcher.forward(request, response);
    }
} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

    

	
	@Override
    public void destroy() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
