<!DOCTYPE html>
<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
		
	<style>
	h1 {
		color:white;
		text-align: center;
	}
	body {
		color: white;
	}
	a {
	 	color: black;
	 	font-size: 25px;
	}
	
	.column { 
		float: left;	
		width: 50%;
	}	
	.column2 {
		float: right;
		width: 50%;
	}
		
	</style>
		
	</head>

    <body onload="initalize()" style="background-image: url(http://a1star.com/images/01-star-best.gif); background-size: contain;"> <!-- Call the initalize method when the page loads -->
    	
    	<div class="container">
			<div>
				<h1><strong>Statistics</strong></h1>
			</div>
			<div class="column">
				<div class="row"><h3>Number of Games Played: </h3></div>
				<div class="row"><h3>Computer Wins: </h3></div>
				<div class="row"><h3>Human Wins: </h3></div>
				<div class="row"><h3>Average Number of Draws: </h3></div>
				<div class="row"><h3>Most Rounds in a Single Game: </h3></div>
			</div>
			
			<div class="column2">
				<div class="row"><h3 id="totalGames"></h3></div>
				<div class="row"><h3 id="computerWins"></h3></div>
				<div class="row"><h3 id="humanWins"></h3></div>
				<div class="row"><h3 id="avgDraws"></h3></div>
				<div class="row"><h3 id="mostRounds"></h3></div>
			</div>
			
			<div>
			<h4 id="statistics"></h4>
			</div>
			
			<div>
			<button type="button" onclick="window.location.href='http://localhost:7777/toptrumps'" style="cursor:pointer; border-radius:12px;">Home</button>
			</div>
		</div>
		
		<script type="text/javascript">
		
			var statistics = "";
			
			var totalGames = "";
			var computerWins = "";
			var humanWins = "";
			var avgDraws = "";
			var mostRounds = "";
			
			var stat1 = "";
			var stat2 = "";
			var stat3 = "";
			var stat4 = "";
			var stat5 = "";
			
		
			// Method that is called on page load
			function initalize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				getTotalGames();
				getComputerWins();
				getHumanWins();
				getAvgDraws();
				getMostRounds();
			
				
			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
		
			// This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}
		
		</script>
		
		<!-- Javascript Functions Below -->
		<script type="text/javascript">

			// Sends request for total number of games played
			function getTotalGames() {

				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getTotalGames"); // Request type and URL+parameters

				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					setElement("totalGames", responseText)
				};

				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			
			// Sends request for number of AI wins
			function getComputerWins() {

				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getComputerWins"); // Request type and URL+parameters

				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					setElement("computerWins", responseText)
				};

				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			
			// Sends request for number of human wins
			function getHumanWins() {

				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getHumanWins"); // Request type and URL+parameters

				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					setElement("humanWins", responseText)
				};

				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			
			// Sends request for average number of draws in a game
			function getAvgDraws() {

				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getAvgDraws"); // Request type and URL+parameters

				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					setElement("avgDraws", responseText)
				};

				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			
			// Sends request for most rounds played in a game
			function getMostRounds() {

				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getMostRounds"); // Request type and URL+parameters

				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					setElement("mostRounds", responseText)
				};

				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			
			// Helper function for setting innerHTML of page elements
			function setElement(id, value) {
				document.getElementById(id).innerHTML = value;
			}
			

		</script>
		
		</body>
</html>