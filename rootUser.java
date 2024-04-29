

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


/**
 * Servlet implementation class rootServlet
 */
public class rootUser extends HttpServlet {

    private Connection con;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project4", "root", "rootMAC1$");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        String sqlCommand = request.getParameter("sqlCommand");

        String executionResults = executeSQLCommand(sqlCommand);

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Execution Results:</h2>");
        out.println("<div class='execution-results'>");
        // New: Generate a table for the results
        out.println("<table border='1'>");

        String[] columnTitles = getColumnTitles(sqlCommand);
        out.println("<tr>");
        for (String title : columnTitles) {
            out.println("<th>" + title + "</th>");
        }
        out.println("</tr>");

        // Remove the "# Column"
        out.println(executionResults);

        out.println("</table>");
        out.println("</div>");
        out.println("</body></html>");
    }

    private String[] getColumnTitles(String sqlCommand) {
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] columnTitles = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnTitles[i - 1] = metaData.getColumnName(i);
            }
            resultSet.close();
            statement.close();
            return columnTitles;
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[]{"Error"};
        }
    }

    private String executeSQLCommand(String sqlCommand) {
        StringBuilder executionResults = new StringBuilder();

        try {
            Statement statement = con.createStatement();
            boolean isResultSet = statement.execute(sqlCommand);

            if (isResultSet) {
                ResultSet resultSet = statement.getResultSet();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Append result rows as table rows
                while (resultSet.next()) {
                    executionResults.append("<tr>");
                    for (int i = 1; i <= columnCount; i++) {
                        executionResults.append("<td>").append(resultSet.getString(i)).append("</td>");
                    }
                    executionResults.append("</tr>");
                }

                resultSet.close();
            } else {
                int updateCount = statement.getUpdateCount();
                if(updateCount > 0) {
                	executionResults.append("<div>");
                	executionResults.append("<h3>Statement executes successfully. ").append(updateCount).append(" row(s) affected.</h3>");
                    
                	if (sqlCommand.contains("insert into shipments") || sqlCommand.contains("update shipments")) {
                        Statement countStatement = con.createStatement();
                        ResultSet countResultSet = countStatement.executeQuery("SELECT COUNT(*) FROM shipments WHERE quantity >= 100");
                        countResultSet.next();
                        int count = countResultSet.getInt(1);
                        countResultSet.close();
                        countStatement.close();
                        if (count > 0) {
                            // Log the status increase for suppliers
                            PreparedStatement updateSupplierStatus = con.prepareStatement("UPDATE suppliers SET status = status + 5 WHERE snum IN (SELECT snum FROM shipments WHERE quantity >= 100)");
                            int rowsUpdated = updateSupplierStatus.executeUpdate();
                            executionResults.append("<p>Business Logic Detected! - Updating Supplier Status</p>");
                            executionResults.append("<p>Business Logic updated ").append(rowsUpdated).append(" of supplier status marks</p>");
                        } else {
                            executionResults.append("<p>Business Logic Not Triggered</p>");
                        }
                    } else {
                        executionResults.append("<p>Business Logic Not Triggered</p>");
                    }
                	executionResults.append("</div>");
                }
                }
            

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            executionResults.append("<tr><td colspan='3'>Error executing SQL command: ").append(e.getMessage()).append("</td></tr>");
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

