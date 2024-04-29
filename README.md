# Serving-Servlet-Looks

## Description
This project involves developing a three-tier web-based application using servlets and JSP technology running on a Tomcat server to access and maintain a MySQL database. The application interacts with a suppliers/parts/jobs/shipments database named project4. Users access the application through an HTML landing page for authentication and are then directed to different JSP pages based on their user level. 

## Project Components
- **Front-End**: HTML landing page for authentication and JSP pages for different user levels.
- **Second-Tier (Servlets)**: Handles user requests, implements business logic, and interacts with the database.
- **Back-End (MySQL Database)**: Stores and maintains the project4 database.

## Key Features
- **Authentication**: Users are authenticated against a credentialsDB via a servlet.
- **User Levels:** Root-level, client-level, data entry-level, and accountant-level users have different privileges and access to functionalities.
- **Business Logic:** Implemented in servlets, such as incrementing supplier status based on shipment quantities.
- **Data Manipulation:** Users can interact with the database through a variety of SQL commands, including prepared statements for safe data entry operations and the ability to execute custom SQL queries. Additionally, users can utilize callable statements to execute remote stored procedures securely.
  
## Technologies Used
- Java
- Servlets
- JSP (JavaServer Pages)
- HTML
- CSS
- JavaScript
- mySQL
