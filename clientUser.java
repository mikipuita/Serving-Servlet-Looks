

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


/**
 * Servlet implementation class clientServlet
 */
public class clientUser extends HttpServlet {

    private Connection con;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project4", "client", "client");
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
                executionResults.append("<tr><td colspan='").append(updateCount).append("'>Update count: ").append(updateCount).append("</td></tr>");
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

