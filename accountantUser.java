import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;


/**
 * Servlet implementation class rootServlet
 */
public class accountantUser extends HttpServlet {
	private Connection con;
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	 // Establish the database connection
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project4", "theaccountant", "theaccountant");
        
        String operation = request.getParameter("operation");
        
        String executionResults = executeStoredProcedure(operation);
        
        request.setAttribute("executionResults", executionResults);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("accountantHome.jsp");
        dispatcher.forward(request, response);



    } catch (SQLException e) {{
    	e.printStackTrace();
    }
} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	

	private String executeStoredProcedure(String operation) {
	    StringBuilder executionResults = new StringBuilder();

	    // Call the corresponding stored procedure based on the selected operation
	    try {
	        CallableStatement stmt = null;

	        // Prepare and execute the stored procedure
	        switch (operation) {
	            case "operation1":
	                stmt = con.prepareCall("{call Get_The_Maximum_Status_Of_All_Suppliers()}");
	                break;
	            case "operation2":
	                stmt = con.prepareCall("{call Get_The_Sum_Of_All_Parts_Weights()}");
	                break;
	            case "operation3":
	                stmt = con.prepareCall("{call Get_The_Total_Number_Of_Shipments()}");
	                break;
	            case "operation4":
	                stmt = con.prepareCall("{call Get_The_Name_Of_The_Job_With_The_Most_Workers()}");
	                break;
	            case "operation5":
	                stmt = con.prepareCall("{call List_The_Name_And_Status_Of_All_Suppliers()}");
	                break;
	            default:
	                executionResults.append("Invalid operation selected");
	        }

	        if (stmt != null) {
	            // Execute the stored procedure
	            ResultSet rs = stmt.executeQuery();

	            // Get column names
	            ResultSetMetaData metaData = rs.getMetaData();
	            int columnCount = metaData.getColumnCount();
	            executionResults.append("<table border=\"1\"><tr>");
	            for (int i = 1; i <= columnCount; i++) {
	                executionResults.append("<th>").append(metaData.getColumnLabel(i)).append("</th>");
	            }
	            executionResults.append("</tr>");

	            // Process the result set and generate the execution results
	            while (rs.next()) {
	                executionResults.append("<tr>");
	                for (int i = 1; i <= columnCount; i++) {
	                    executionResults.append("<td>").append(rs.getString(i)).append("</td>");
	                }
	                executionResults.append("</tr>");
	            }
	            executionResults.append("</table>");

	            // Close statement and result set
	            rs.close();
	            stmt.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle SQL exception
	        executionResults.append("Error executing stored procedure: ").append(e.getMessage());
	    }

	    return executionResults.toString();
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
