
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to the Spring 2024 Project 4 Enterprise System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0; /* Light gray background */
            color: #333; /* Dark gray text color */
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff; /* White background for container */
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Shadow effect */
        }

        h1 {
            font-size: 24px;
            font-weight: bold;
            color: #333; /* Dark gray color for title */
            margin-bottom: 20px; /* Margin below title */
        }

        p {
            margin-bottom: 10px; /* Margin below paragraphs */
            color: darkgoldenrod;
        }
        
        hr {
            margin: 20px 0;
            border: none;
            border-top: 1px solid #ccc;
        }

        .input-area {
            width: 100%;
            height: 200px;
            margin-bottom: 20px; /* Margin below input area */
            padding: 10px;
            border: 1px solid #ccc; /* Light gray border */
            border-radius: 4px;
            resize: none; /* Prevent resizing */
        }

        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-right: 10px; /* Margin between buttons */
        }

        .btn-green {
            background-color: green;
            color: #fff; /* White text color */
        }

        .btn-red {
            background-color: red;
            color: #fff; /* White text color */
        }

        .btn-yellow {
            background-color: orange;
            color: #fff; /* White text color */
        }

        .result-container {
            border-top: 1px solid #ccc; /* Light gray border on top */
            padding-top: 20px; /* Padding on top of result container */
        }

        .execution-results {
            font-family: monospace; /* Use monospace font for results */
        }
        .black-text {
            color: black;
        }
        /* Custom styles for radio buttons */
        .radio-container {
            margin-bottom: 10px;
        }

        .radio-container label {
            display: block;
            margin-bottom: 5px;
            color: black;
        }

        .radio-container input[type="radio"] {
            margin-right: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to the Spring 2024 Project 4 Enterprise System</h1>
        <p>A Servlet/JSP-based Multi-tiered Enterprise Application Using a Tomcat Container</p>
        
        <div>
            <hr> 
            <p class="black-text">You are connected to the Project 4 Enterprise System database as an <strong>accountant-level</strong> user. <br> Please select the operation you would like to perform from the list below.</p>
            <br><br><br>
           <!-- Radio button options -->
           <form id="reportForm" action="accountantUser" method="post">
            <div class="radio-container">
                <label>
                    <input type="radio" name="operation" value="operation1"> <strong>Get The Maximum Status Value Of All Suppliers </strong>(Returns a maximum value)
                </label><br>
            </div>
            <div class="radio-container">
                <label>
                    <input type="radio" name="operation" value="operation2"> <strong>Get The Total Weight Of All Parts </strong>(Returns a sum)
                </label><br>
            </div>
            <div class="radio-container">
                <label>
                    <input type="radio" name="operation" value="operation3"> <strong>Get The Total Number of Shipments </strong>(Returns the current number of shipments in total)
                </label><br>
            </div>
            <div class="radio-container">
                <label>
                    <input type="radio" name="operation" value="operation4"> <strong>Get The Name And Number Of Workers Of The Job With The Most Workers </strong>(Returns two values)
                </label> <br>
            </div>
            <div class="radio-container">
                <label>
                    <input type="radio" name="operation" value="operation5"> <strong>List The Name And Status Of Every Supplier </strong>(Returns a list of supplier names with status)
                </label>
            </div>
            <br><br><br>
            <button class="btn btn-green">Execute Command</button>
            </form>
            <button id="clearResults" class="btn btn-red">Clear Results</button>
            <br>
            <p class="black-text"> All execution results will appear below this line. </p><br>

        </div>
        
        <div class="result-container">
            <h2>Execution Results:</h2>
            <div class="execution-results" id="executionResults">
                <% 
                    String executionResults = (String) request.getAttribute("executionResults"); 
                    out.println(executionResults != null ? executionResults : ""); 
                    %>
            </div>
        </div>
    </div>
    <script>document.getElementById("clearResults").addEventListener("click", function() {
        document.getElementById("reportForm").reset(); // Reset the form
        document.getElementById("executionResults").innerHTML = ""
    });</script>
</body>
</html>
