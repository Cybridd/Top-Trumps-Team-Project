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
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script> vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
		
		<link rel="shortcut icon" href="">

		<style>
		h1{
			color: white;
			text-align: center;
		}
		h2 {
			color: white;
		}
		.card-img-top{
  			width:100%;
  			height:7vw;
		}
		.dropdown-menu{
			background-color: transparent;
		}
		</style>
	</head>
	<div>
	</br>
		<h1><strong>Top Trumps Game</strong></h1>
	</div>
	<hr>
    <body onload="initalize()" style="background-image:url(http://a1star.com/images/01-star-best.gif); background-size: contain;"> <!-- Call the initalize method when the page loads -->
    
    <div class="container">
    	<div class="row">
    		<div class="col-md-3">
    				<button id="startButton" onclick="startRound()">Start Round</button>
    			<br>
          		<br>
         			<button id="playButton" onclick="playRound()">Play Round</button>
          		<br>
          		<br>
         			<button id="finishButton" onclick="finishRound()">Finish Round</button>
    			<br>
    			<br>
    			<div class="dropdown">
					<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" id="buttonmenu">Select Attribute</button>
					<ul class="dropdown-menu">
						<li><button type="button" id="button1" onclick="setHumanChoice(1)"></button></li>
						<li><button type="button" id="button2" onclick="setHumanChoice(2)"></button></li>
						<li><button type="button" id="button3" onclick="setHumanChoice(3)"></button></li>
						<li><button type="button" id="button4" onclick="setHumanChoice(4)"></button></li>
						<li><button type="button" id="button5" onclick="setHumanChoice(5)"></button></li>
					</ul>

				</div>
				<br>
				<br>		
        		<h2 id="activePlayerInfo"></h2>
        		<h2 id="attChoice"></h2>
        		<h2 id="communalPileSize"></h2>
                <h2 id="roundResult"></h2>
                <h2 id="winner"></h2>
                <h2 id="gameStats"></h2>
    		</div>
    		<div class="col-md">
    			<div class="card" id="card1" style="width:200px">
					<img class="card-img-top" id="image1" src="https://pbs.twimg.com/profile_images/877626864587554816/bT4oEO1U_400x400.jpg" alt="Image Here">
					<div class="card-header" id="player1Name"></div>
					<div class="card-body">
						<h3 class="card-text" id="handSize1"></h3>
						<div class="card-title" id="cardName1"></div>
						<div class="card-text" id="p1att1"></div>
						<div class="card-text" id="p1att2"></div>
						<div class="card-text" id="p1att3"></div>
						<div class="card-text" id="p1att4"></div>
						<div class="card-text" id="p1att5"></div>
					</div>
				</div>
			</div>
			<div class="col-md">	
				<div class="card" id="card2" style="width:200px">
					<img class="card-img-top" id="image2" src="https://pbs.twimg.com/profile_images/877626864587554816/bT4oEO1U_400x400.jpg" alt="Image Here">
					<div class="card-header" id="player2Name"></div>
					<div class="card-body">
						<h3 class="card-text" id="handSize2"></h3>
						<div class="card-title" id="cardName2"></div>
						<div class="card-text" id="p2att1"></div>
						<div class="card-text" id="p2att2"></div>
						<div class="card-text" id="p2att3"></div>
						<div class="card-text" id="p2att4"></div>
						<div class="card-text" id="p2att5"></div>
					</div>
				</div>
				<br>
				<div class="card" id="card3" style="width:200px">
					<img class="card-img-top" id="image3" src="https://pbs.twimg.com/profile_images/877626864587554816/bT4oEO1U_400x400.jpg" alt="Image Here">
					<div class="card-header" id="player3Name"></div>
					<div class="card-body">
						<h3 class="card-text" id="handSize3"></h3>
						<div class="card-title" id="cardName3"></div>
						<div class="card-text" id="p3att1"></div>
						<div class="card-text" id="p3att2"></div>
						<div class="card-text" id="p3att3"></div>
						<div class="card-text" id="p3att4"></div>
						<div class="card-text" id="p3att5"></div>
					</div>
				</div>
    		</div>
    		<div class="col-md">
    			<div class="card" id="card4" style="width:200px">
					<img class="card-img-top" id="image4"  src="https://pbs.twimg.com/profile_images/877626864587554816/bT4oEO1U_400x400.jpg" alt="Image Here">
					<div class="card-header" id="player4Name"></div>
					<div class="card-body">
						<h3 class="card-text" id="handSize4"></h3>
						<div class="card-title" id="cardName4"></div>
						<div class="card-text" id="p4att1"></div>
						<div class="card-text" id="p4att2"></div>
						<div class="card-text" id="p4att3"></div>
						<div class="card-text" id="p4att4"></div>
						<div class="card-text" id="p4att5"></div>
					</div>
				</div>
				<br>
				<div class="card" id="card5" style="width:200px">
					<img class="card-img-top" id="image5"  src="https://pbs.twimg.com/profile_images/877626864587554816/bT4oEO1U_400x400.jpg" alt="Image Here">
					<div class="card-header" id="player5Name"></div>
					<div class="card-body">
						<h3 class="card-text" id="handSize5"></h3>
						<div class="card-title" id="cardName5"></div>
						<div class="card-text" id="p5att1"></div>
						<div class="card-text" id="p5att2"></div>
						<div class="card-text" id="p5att3"></div>
						<div class="card-text" id="p5att4"></div>
						<div class="card-text" id="p5att5"></div>
					</div>
				</div>
			</div>
    	</div>
			<div class="row justify-content-md-center" id="otherButtons">
				<form action="http://localhost:7777/toptrumps/">
					<input type="submit" value="Quit" />
				</form>
			</div>
	</div>

		<script type="text/javascript">
		
		var winner = "";
		var gameStats = "";
		var pileSize = "";
		
		var card1 = "";
		var card2 = "";
		var card3 = "";
		var card4 = "";
		var card5 = "";
		var playerNames = "";
		var player1Name = "None";
		var player2Name = "None";
		var player3Name = "None";
		var player4Name = "None";
		var player5Name = "None";
		var attributes = "";
		var attribute1 = "";
		var attribute2 = "";
		var attribute3 = "";
		var attribute4 = "";
		var attribute5 = "";
		
		var p1att1 = "";
		var p1att2 = "";
		var p1att3 = "";
		var p1att4 = "";
		var p1att5 = "";
		
		
		var p2att1 = "";
		var p2att2 = "";
		var p2att3 = "";
		var p2att4 = "";
		var p2att5 = "";
		
		
		var p3att1 = "-";
		var p3att2 = "-";
		var p3att3 = "-";
		var p3att4 = "-";
		var p3att5 = "-";
		
		var p4att1 = "-";
		var p4att2 = "-";
		var p4att3 = "-";
		var p4att4 = "-";
		var p4att5 = "-";
		
		var p5att1 = "-";
		var p5att2 = "-";
		var p5att3 = "-";
		var p5att4 = "-";
		var p5att5 = "-";
		var currentCardNames = "";
		var cardName1 = "";
		var cardName2 = "";
		var cardName3 = "";
		var cardName4 = "";
		var cardName5 = "";
		
		var button1 = "";
		var button2 = "";
		var button3 = "";
		var button4 = "";
		var button5 = "";
		var numPlayers = Number(findGetParameter("numOpponents")) + 1;
		var maxPlayers = 5;
		var activePlayerInfo = "";
		
			// Method that is called on page load
			function initalize() {
			startGame();
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
			}
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
			function getData()
			{
				getPlayerNames();
				getDescripts();
			}
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
			// Function for retrieving the parameters in the GET request for this webpage
			function findGetParameter(parameterName) {
    			var result = null;
       			tmp = [];
    			location.search.substr(1).split("&").forEach(function (item) {
         			tmp = item.split("=");
          		if (tmp[0] === parameterName) result = decodeURIComponent(tmp[1]);
        		});
    			return result;
			}
		</script>

		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
		
		// Asks the API to start an instance of GameRunner, with arguments retrieved from the GET request
			function startGame() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/createGame?name=" + findGetParameter("name") + "&numOpponents=" + findGetParameter("numOpponents")); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORSS
				if (!xhr) {
  					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response, confirms game creation
 					getPlayerNames();  // Get names of all players from game
					getDescripts();  // Get descriptions of card attributes
					for (i = numPlayers; i < maxPlayers; i++){
						document.getElementById("card" + (i+1)).style.display = "none"; // Hide cards of any non-used player slots
					}
					alert(responseText); // lets produce an alert
					
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
				
				document.getElementById("finishButton").disabled = true;
			}
			
		// Sends a request to start a round of top trumps to the API (and update display)
			function startRound() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/startRound"); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORSS
				if (!xhr) {
  					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
				var delayinMS = 1000;
				getActivePlayer();  // Get current active player from game
				getCurrentCardNames();  // Get names of current cards in play
				getHandSizes();  // Get number of cards belonging to each player
				getCommunalPileSize();  // Get number of cards in communal pile
				for (i = 0; i < numPlayers; i++){
 					getAttributes(i);  // Get current card attributes
 					getImage(i);  // Source relevant images for current cards
				}
				// Toggle activation of buttons
				document.getElementById("startButton").disabled = true;
				document.getElementById("playButton").disabled = false;
				document.getElementById("finishButton").disabled = true;
				setElement("roundResult", "");  // Delete previous round result
				setElement("attChoice", "Attribute chosen: ");  // Delete previous attribute chosen
			}
			
			// Send a request to play a round of top trumps to the API (and update display)
			function playRound() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/playRound"); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORSS
				if (!xhr) {
  					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					setElement("attChoice","Attribute chosen: " + attDescripts[responseText]);  // Display attribute that was chosen
 
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
				unHideCards();  // Reveal opponents cards
				// Toggle activation of buttons
				document.getElementById("startButton").disabled = true;
				document.getElementById("playButton").disabled = true;
				document.getElementById("finishButton").disabled = false;
			}
			
			// Send a request to complete a round of top trumps (and update display)
			function finishRound() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/finishRound"); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORSS
				if (!xhr) {
  					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					setElement("roundResult", responseText);  // Display result of round
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
				// Toggle activation of buttons
				document.getElementById("startButton").disabled = false;
				document.getElementById("playButton").disabled = true;
				document.getElementById("finishButton").disabled = true;
				// Check for game winner
 				isWinner();
				if(winner == 'true'){
				document.getElementById("startButton").disabled = true;
				getGameStats();
				}
				
			}
			
			// Sends a request for the names of the current players in the game
			function getPlayerNames() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getPlayerNames"); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					playerNames = JSON.parse(responseText);
 					for (i = 0; i < numPlayers; i++){
 					setElement("player" + (i+1) + "Name","Player " + (i+1) + ": " + playerNames[i]);  // Display player names
					}
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			
			// Sends a request for the current number of cards belonging to each player
			function getHandSizes() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getHandSizes"); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					handSizes = JSON.parse(responseText);
 					for (i = 0; i < numPlayers; i++){
 					setElement("handSize" + (i+1),"Cards: " + handSizes[i]);  // Display cards remaining for each player
					}
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			
			// Sends a request for the current active player in the game
			function getActivePlayer() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getActivePlayer"); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					activePlayer = responseText;
 					document.getElementById("activePlayerInfo").innerHTML = "Active Player: " + activePlayer;
 					if(activePlayer == "1"){
 					deactivateButtons("false");
 					document.getElementById("playButton").disabled = true;
 					}
 					else{
 					deactivateButtons("true");  // If human is not active player, deactivate selection menu
 					}
 					hideCards(activePlayer);  // If human is active player, hide opponents cards
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			
			// Sends a request for the attribute descriptions to the deck (via gamerunner)
			function getDescripts() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getDescripts"); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					attDescripts = JSON.parse(responseText);
 					document.getElementById("button1").innerHTML = attDescripts[0];  // Set dropdown menu to display descriptions
 					document.getElementById("button2").innerHTML = attDescripts[1];
 					document.getElementById("button3").innerHTML = attDescripts[2];
 					document.getElementById("button4").innerHTML = attDescripts[3];
 					document.getElementById("button5").innerHTML = attDescripts[4];
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			
			// Sends a request for the current card objects in play
			function getCurrentCards() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCurrentCards"); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					currentCards = JSON.parse(responseText);
					alert(responseText); // lets produce an alert
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			
			// Sends a request for the names of the current cards in play
			function getCurrentCardNames() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCurrentCardNames"); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					currentCardNames = JSON.parse(responseText);
					for (i = 0; i < numPlayers; i++){
 					setElement("cardName"+ (i+1),currentCardNames[i]);
					}
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			
			// Sends a request for the current size of the communal pile
			function getCommunalPileSize() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCommunalPileSize"); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					pileSize = JSON.parse(responseText);
 					setElement("communalPileSize","Communal Pile Cards : " + pileSize);  // Display the communal pile size
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			
			// Sends a request for the attributes of the current cards in play
			function getAttributes(player) {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getAttributes?player="+player); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					attributes = JSON.parse(responseText);
 					setElement(("p" + (player+1) + "att1"),"Size : " + attributes[0]);  // Display attribute numbers for each card
 					setElement(("p" + (player+1) + "att2"),"Speed : " + attributes[1]);
 					setElement(("p" + (player+1) + "att3"),"Range : " + attributes[2]);
 					setElement(("p" + (player+1) + "att4"),"Firepower : " + attributes[3]);
 					setElement(("p" + (player+1) + "att5"),"Cargo : " + attributes[4]);
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			
			// Sends human selection (available when human is active player)
			function setHumanChoice(choice) {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/setHumanChoice?choice=" + choice); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
				
				document.getElementById("playButton").disabled = false;  // Toggle button activation
			}
			
			// Sends a request for boolean isWinner value from game
			function isWinner() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/isWinner"); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					winner = responseText;
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			
			// Sends a request for statistics on game just played
			function getGameStats() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getGameStats"); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					setElement("gameStats",responseText);
 					setElement("winner","The winner of the game is Player " + activePlayer); 
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			
			// Requests name of a given card from game and retrieves relevant picture
			function getImage(card) {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getImageName?card=" + card); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					var c = document.getElementById("image" + (card+1));
					if(responseText != "None"){  
						c.src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + responseText + ".jpg"; // Retrieve picture from database
					} else {  // If player is eliminated, display alternate funeral
						c.src = "https://robertsspaceindustries.com/media/ye4m24rxgqdgor/source/Wc1-Funeral_thumb-E1360096322779-300x115.jpg";
					}
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			
			// Helper function for setting innerHTML of page elements
			function setElement(id, value){
			document.getElementById(id).innerHTML = value;
			}
			
			// Function for deactivating attribute dropdown menu and attribute buttons
			function deactivateButtons(value){
				if(value == "true"){
				document.getElementById("buttonmenu").disabled = true;
				}
				else{
				document.getElementById("buttonmenu").disabled = false;
				}
				for (i = 0; i < 5; i++){
					if(value == "true"){
 					document.getElementById("button" + (i+1)).disabled = true;
 					}
 					else{
 					document.getElementById("button" + (i+1)).disabled = false;
 					}
				}
			}
			
			// Function for hiding opponents cards when human is active player
			function hideCards(activePlayer){
				for (i = 1; i < numPlayers; i++){
					var c = document.getElementById("card" + (i+1));
    				if (activePlayer == "1") {
        			c.style.display = "none";
    				} else {
       				c.style.display = "block";
    				}
				}
			}
			
			// Function for revealing cards when Play Round is pressed
			function unHideCards(){
				for (i = 1; i < numPlayers; i++){
					var c = document.getElementById("card" + (i+1));
       				c.style.display = "block";
				}
			}
			
		</script>

		</body>
</html>