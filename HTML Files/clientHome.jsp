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
            color: blue;
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
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to the Spring 2024 Project 4 Enterprise System</h1>
        <p>A Servlet/JSP-based Multi-tiered Enterprise Application Using a Tomcat Container</p>
        
        <div>
            <hr> 
            <p class="black-text">You are connected to the Project 4 Enterprise System database as a <strong>client-level</strong> user. <br> Please enter any SQL query or update command in the box below.</p>
            <hr>
            <form id="sqlForm" action="clientUser" method="post">
                <textarea id="sqlCommand" class="input-area" name="sqlCommand" placeholder="Enter your SQL query or update command here..."></textarea>
                <br>
                <button id="executeBtn" class="btn btn-green">Execute Command</button>
            </form>
                <button id="resetBtn" class="btn btn-red">Reset Form</button>
                <button id="clearBtn" class="btn btn-yellow">Clear Results</button>
      
        </div>
        
        <div class="result-container" id="resultContainer">
            <h2>Execution Results:</h2>
            <div class="execution-results" id="executionResults">
                <!-- Execution results will appear here -->
            </div>
        </div>
    </div>

    <script>
        document.getElementById("executeBtn").addEventListener("click", function(event) {
            event.preventDefault(); // Prevent default button click behavior

            var sqlCommand = document.getElementById("sqlCommand").value; // Get SQL command from textarea
            var xhr = new XMLHttpRequest(); // Create XMLHttpRequest object

            xhr.onreadystatechange = function() {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        // Update the execution results container with the response from the server
                        document.getElementById("executionResults").innerHTML = xhr.responseText;
                    } else {
                        // Handle errors
                        console.error("Error:", xhr.statusText);
                    }
                }
            };

            xhr.open("POST", "clientUser", true); // Open POST request to rootUser servlet
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.send("sqlCommand=" + encodeURIComponent(sqlCommand)); // Send SQL command as data
        });

        document.getElementById("resetBtn").addEventListener("click", function() {
            document.getElementById("sqlForm").reset(); // Reset the form
        });

        document.getElementById("clearBtn").addEventListener("click", function() {
            document.getElementById("executionResults").innerHTML = ""; // Clear execution results
        });
    </script>
</body>
</html>
