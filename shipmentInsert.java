import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;


/**
 * Servlet implementation class rootServlet
 */
public class shipmentInsert extends HttpServlet {
	private Connection con;
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	 // Establish the database connection
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project4", "dataentryuser", "dataentryuser");

        // Retrieve form parameters
        String snum = request.getParameter("snumm");
        String pnum = request.getParameter("pnumm");
        String jnum = request.getParameter("jnumm");
        String quantity = request.getParameter("quantity");


        // Prepare and execute the update statement
        PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO shipments (snum, pnum, jnum, quantity) VALUES (?, ?, ?, ?)");
        preparedStatement.setString(1, snum);
        preparedStatement.setString(2, pnum);
        preparedStatement.setString(3, jnum);
        preparedStatement.setString(4, quantity);
        

        
        int rowsAffected = preparedStatement.executeUpdate();
        preparedStatement.close();
        
        //Apply business logic
        if (Integer.parseInt(quantity) >= 100) {
        	PreparedStatement updateSupplierStatus = con.prepareStatement("UPDATE suppliers SET status = status + 5 WHERE snum IN (SELECT snum FROM shipments WHERE quantity >= 100)");
        	updateSupplierStatus.executeUpdate();
        }
        
        // Set the response content type
        response.setContentType("text/html");

     // Construct the execution results message
        String executionResults;
        if (rowsAffected > 0) {
            executionResults = "<h2>New shipments record:</h2><p>(" + snum + ", " + pnum + ", " + jnum + ", " +  quantity + ") - Successfully entered into database</p>";
            if(Integer.parseInt(quantity)>= 100) {
            	executionResults += " - Business Logic Triggered";
            } else {
            	executionResults += " - Business Logic not Triggered";
            }
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
