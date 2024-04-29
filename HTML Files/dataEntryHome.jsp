<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            color: darkred;
        }
        
        hr {
            margin: 20px 0;
            border: none;
            border-top: 1px solid #ccc;
        }

        .insert-box {
            margin-bottom: 20px;
        }

        .insert-box h2 {
            margin-bottom: 10px;
            font-size: 18px; /* Smaller font size for section title */
        }

        .insert-table {
            width: 100%;
            border-collapse: collapse;
        }

        .insert-row {
            display: table-row-group; /* Changed to display as table-row-group */
        }

        .insert-cell {
            display: table-cell;
            padding: 5px;
        }

        .insert-cell label {
            display: inline-block;
            width: 80px; /* Fixed width for labels */
            text-align: right;
            margin-right: 10px;
        }

        .insert-cell input {
            box-sizing: border-box;
        }
        .insert-cell label, .insert-cell input {
            display: block;
        }
        
        .insert-row input[type="text"] {
            width: calc(100% - 10px);
            padding : 10px; 
            box-sizing: border-box;
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
        .black-text {
            color: black;
        }

        .result-container {
            border-top: 1px solid #ccc; /* Light gray border on top */
            padding-top: 20px; /* Padding on top of result container */
        }

        .execution-results {
            font-family: monospace; /* Use monospace font for results */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to the Spring 2024 Project 4 Enterprise System</h1>
        <p>Data Entry Application</p>
        
        <div>
            <hr> 
            <p class="black-text">You are connected to the Project 4 Enterprise System database as a <strong>data-entry-level</strong> user. <br>Enter the data values in a form below to add a new record to the corresponding database table.</p>
        </div>
        <hr> 
        
        <div class="insert-box">
            <h2>Supplier Record Insert</h2>
            <form id="supplierForm" action="supplierInsert" method="post">
                <table class="insert-table">
                    <tr class="insert-row">
                        <td class="insert-cell"><label for="snum">snum:</label></td>
                        <td class="insert-cell"><label for="sname">sname:</label></td>
                        <td class="insert-cell"><label for="status">status:</label></td>
                        <td class="insert-cell"><label for="city">city:</label></td>
                        
                    </tr>
                    <tr class="insert-row">
                        <td class="insert-cell"><input type="text" id="snum" name="snum"></td>
                        <td class="insert-cell"><input type="text" id="sname" name="sname"></td>
                        <td class="insert-cell"><input type="text" id="status" name="status"></td>
                        <td class="insert-cell"><input type="text" id="city" name="city"></td>
                    </tr>
                </table>
                <button id ="executeBtn" class="btn btn-green">Enter Supplier Record Into Database</button>
                </form>
                <button id = "clearDataandResults" class="btn btn-red">Clear Data and Results</button>
           
        </div>

        <div class="insert-box">
            <h2>Parts Record Insert</h2>
            <form id="partForm" action="partInsert" method="post">
                <table class="insert-table">
                    <tr class="insert-row">
                        <td class="insert-cell"><label for="pnum">pnum:</label></td>
                        <td class="insert-cell"><label for="pname">pname:</label></td>
                        <td class="insert-cell"><label for="color">color:</label></td>
                        <td class="insert-cell"><label for="weight">weight:</label></td>
                        <td class="insert-cell"><label for="cityy">city:</label></td>
                    </tr>
                    <tr class="insert-row">
                        <td class="insert-cell"><input type="text" id="pnum" name="pnum"></td>
                        <td class="insert-cell"><input type="text" id="pname" name="pname"></td>
                        <td class="insert-cell"><input type="text" id="color" name="color"></td>
                        <td class="insert-cell"><input type="text" id="weight" name="weight"></td>
                        <td class="insert-cell"><input type="text" id="cityy" name="cityy"></td>
                    </tr>
                </table>
                <button id="executeBtn2" class="btn btn-green">Enter Parts Record Into Database</button>
                </form>
                <button id = "clearDataandResults2" class="btn btn-red">Clear Data and Results</button>
      
        </div>

        <div class="insert-box">
            <h2>Jobs Record Insert</h2>
            <form id="jobForm" action="jobInsert" method="post">
                <table class="insert-table">
                    <tr class="insert-row">
                        <td class="insert-cell"><label for="jnum">jnum:</label></td>
                        <td class="insert-cell"><label for="jname">jname:</label></td>
                        <td class="insert-cell"><label for="numworkers">numworkers:</label></td>
                        <td class="insert-cell"><label for="cityyy">city:</label></td>
                    </tr>
                    <tr class="insert-row">
                        <td class="insert-cell"><input type="text" id="jnum" name="jnum"></td>
                        <td class="insert-cell"><input type="text" id="jname" name="jname"></td>
                        <td class="insert-cell"><input type="text" id="numworkers" name="numworkers"></td>
                        <td class="insert-cell"><input type="text" id="cityyy" name="cityyy"></td>
                    </tr>
                </table>
                <button id="executeBtn3" class="btn btn-green">Enter Jobs Record Into Database</button>
                </form>
                <button id="clearDataandResults3"class="btn btn-red">Clear Data and Results</button>
           
        </div>

        <div class="insert-box">
            <h2>Shipment Record Insert</h2>
            <form id="shipmentForm" action="shipmentInsert" method="post">
                <table class="insert-table">
                    <tr class="insert-row">
                        <td class="insert-cell"><label for="snumm">snum:</label></td>          
                        <td class="insert-cell"><label for="pnumm">pnum:</label></td>
                        <td class="insert-cell"><label for="jnumm">jnum:</label></td>
                        <td class="insert-cell"><label for="quantity">quantity:</label></td>
                    </tr>
                    <tr class="insert-row">
                        <td class="insert-cell"><input type="text" id="snumm" name="snumm"></td>
                        <td class="insert-cell"><input type="text" id="pnumm" name="pnumm"></td>
                        <td class="insert-cell"><input type="text" id="jnumm" name="jnumm"></td>
                        <td class="insert-cell"><input type="text" id="quantity" name="quantity"></td>
                    </tr>
                </table>
                <button id="executeBtn3" class="btn btn-green">Enter Shipment Record Into Database</button>
                </form>
                <button id="clearDataandResults4"class="btn btn-red">Clear Data and Results</button>
          
        </div>

        <div class="result-container">
            <h2>Execution Results:</h2>
            <div class="execution-results" id="executionResults">
            <% String executionResults = (String) request.getAttribute("executionResults"); %>
                <%= executionResults != null ? executionResults : "" %>
                <!-- Execution results will appear here -->
            </div>
        </div>
    </div>
    <script>
 
    document.getElementById("clearDataandResults").addEventListener("click", function() {
        document.getElementById("supplierForm").reset(); // Reset the form
        document.getElementById("executionResults").innerHTML = ""
    });
    
    document.getElementById("clearDataandResults2").addEventListener("click", function() {
        document.getElementById("partForm").reset(); // Reset the form
        document.getElementById("executionResults").innerHTML = ""
    });
    
    document.getElementById("clearDataandResults3").addEventListener("click", function() {
        document.getElementById("jobForm").reset(); // Reset the form
        document.getElementById("executionResults").innerHTML = ""
    });
    
    document.getElementById("clearDataandResults4").addEventListener("click", function() {
        document.getElementById("shipmentForm").reset(); // Reset the form
        document.getElementById("executionResults").innerHTML = ""
    });
</script>
    
</body>
</html>
