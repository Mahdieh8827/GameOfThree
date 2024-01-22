let stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    $("#gameOverMessage").hide();
    if (connected) {
        $("#gameMessage").hide();
    }
    else {
        $("#playerName").prop("disabled", false);
    	$("#start").hide();
    	$("#start-computer").hide();
        $("#gameMessage").hide();
        $("#gameStatus").hide();
    }
}

function connect() {
	try {
	const socket = new SockJS('/game-of-three-websocket');
	stompClient = StompJs.Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/gameOfThree', function (gameStatus) {
		try{
			const currentGameStatus = JSON.parse(gameStatus.body);
			gameStatus.lastPlayer = currentGameStatus.lastPlayer;
            showGameStatus(currentGameStatus);

            $("#currentNumber").val(currentGameStatus.currentNumber);
            $("#lastPlayer").val(currentGameStatus.lastPlayer);
            $("#moveByMinusOne").prop("disabled", isCurrentPlayer(currentGameStatus.lastPlayer));
            $("#moveByZero").prop("disabled", isCurrentPlayer(currentGameStatus.lastPlayer));
            $("#moveByOne").prop("disabled", isCurrentPlayer(currentGameStatus.lastPlayer));
		} catch (error) {
			console.error('Error while processing game status:', error);
			showError('Error while processing game status. Please try again.');
		}
        });
    });
	} catch (error) {
		console.error('Error during WebSocket connection:', error);
		showError('Error during WebSocket connection. Please try again.');
	}
}

function showError(message) {
	// Display the error message to the user, you can use a modal, alert, or update a dedicated error div in your UI
	console.error(message);
	// For example, updating a dedicated error div in the UI
	$('#errorDiv').text(message);
}

function endGame(lastPlayer) {
    $("#gameMessage").hide();
    if($("#playerName").val() === lastPlayer) {
    	$("#gameOverMessage").replaceWith(
    			"<div id='gameOverMessage' class='alert alert-success'>"
    			+"Game over, the winner is : " + lastPlayer
    			+"</div>"
    	);
    } else {
    	$("#gameOverMessage").replaceWith(
    			"<div id='gameOverMessage' class='alert alert-danger'>"
    			+"Game over, the winner is : " + lastPlayer
    			+"</div>"
    	);
	}
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function startGame(gameMode) {
    stompClient.send("/app/start", {}, JSON.stringify({'playerName':$("#playerName").val(), 'gameMode':gameMode}));
    $("#playerName").prop("disabled", true);
    $("#start").hide();
    $("#start-computer").hide();
    $("#gameStatus").show();
    $("#gameMode").val(gameMode);
}

function move(inputNumber) {
	if((parseInt($("#currentNumber").val()) + inputNumber) % 3 !== 0) {
		$("#gameStatus").append(
    			"<div class='alert alert-danger'>"
    			+"Sorry, invalid input number. It has to be dividable by 3 !"
    			+"</div>"
    	);
	} else {
		stompClient.send("/app/move", {}, JSON.stringify({'currentNumber': $("#currentNumber").val(), 'player': $("#playerName").val(), 'inputNumber': inputNumber, 'gameMode': $("#gameMode").val()}));
	}
}

function showGameStatus(gameStatus) {
    if(gameStatus.status === 'WAITING_FOR_PLAYER') {
    	if(isCurrentPlayer(gameStatus.lastPlayer) && gameStatus.gameMode !== 'COMPUTER') {
    		$("#gameStatus").append(
    				"<div class='alert alert-info'>"
    				+"<p>Waiting for another player</p>"
    				+"</div>"
    		);
    	}
    } else {
    	if(gameStatus.lastNumberAdded != null) {
    		$("#gameStatus").append(
    				"<div class='alert " + classToUse(gameStatus.lastPlayer) + "'>" 
    				+ "<b>" + gameStatus.lastPlayer + "</b>" + " played <b>" + gameStatus.lastNumberAdded + "</b>"
    				+"<br>"
    				+"<b>"
    				+ gameStatus.currentNumber
    				+"</b>"
    				+"</div>"
    		);
    	} else {
    		$("#gameStatus").append(
        			"<div class='alert " + classToUse(gameStatus.lastPlayer) + "'>" 
        			+"<b>"
        			+ gameStatus.currentNumber
        			+"</b>"
        			+"</div>"
        	);
    	}
    	if(gameStatus.status === 'IN_PROGRESS') {
    		$("#gameMessage").show();
    	} else if(gameStatus.status === 'OVER') {
	    	$("#gameMessage").hide();
	        if(isCurrentPlayer(gameStatus.lastPlayer)) {
	        	$("#gameStatus").append(
	        			"<div class='alert alert-success'>"
	        			+"Congratulations ! You won !"
	        			+"<br>Refresh the browser to start a new game"
	        			+"</div>"
	        	);
	        } else {
	        	$("#gameStatus").append(
	        			"<div class='alert alert-danger'>"
	        			+"You lost ! Hard luck !"
	        			+"<br>Refresh the browser to start a new game"
	        			+"</div>"
	        	);
				$("#moveByMinusOne").disable();
				$("#moveByZero").disable();
				$("#moveByOne").disable();
	    	}
	    }
	}
}

function classToUse(lastPlayer) {
	if(isCurrentPlayer(lastPlayer))
		return "alert-info";
	else {
		return "alert-warning";
	} 
}

function isCurrentPlayer(lastPlayer) {
	return $("#playerName").val() === lastPlayer;
}

function onChangePlayerName () {
	$("#start").prop("disabled", $( "#playerName").val() === '');
	$("#start-computer").prop("disabled", $( "#playerName").val() === '');
}

$(function () {
	$.get("/isAPlayerWaiting", function( data ) {
		  if(data === true) {
			  $("#start-computer").hide();
			  $("#gameStatus").append(
	    				"<div class='alert alert-info'>"
	    				+"<p>A player is waiting for an opponent</p>"
	    				+"</div>"
	    		);
		  }
		});
	
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#start" ).click(function() { startGame('HUMAN'); });
    $( "#start-computer" ).click(function() { startGame('COMPUTER'); });

    $( "#moveByMinusOne" ).click(function() { move(-1); });
    $( "#moveByZero" ).click(function() { move(0); });
    $( "#moveByOne" ).click(function() { move(1); });
    $( "#playerName").change(function() { onChangePlayerName(); });
    
    $( document ).ready(function() { connect();onChangePlayerName(); });

    $("#connect").prop("disabled", true);
    
    $("#gameMessage").hide();
    
});