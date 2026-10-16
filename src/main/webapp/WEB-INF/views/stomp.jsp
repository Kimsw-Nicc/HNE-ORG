<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<html>
<head>
<title>Hello WebSocket</title>
<script src="/resources/assets/js/sockjs-0.3.min.js"></script>
<script src="/resources/assets/js/stomp.min.js"></script>
<script src="/resources/assets/js/jquery.3.2.1.min.js"></script>
</head>
<body>
	<noscript>
		<h2 style="color: #ff0000">Seems your browser doesn't support
			Javascript! Websocket relies on Javascript being enabled. Please
			enable Javascript and reload this page!</h2>
	</noscript>
	<div id="main-content" class="container">
		<div class="row">
			<div class="col-md-6">
				<form class="form-inline">
					<div class="form-group">
						<label for="connect">WebSocket connection:</label>
						<button id="connect" class="btn btn-default" type="submit">Connect</button>
						<button id="disconnect" class="btn btn-default" type="submit"
							disabled="disabled">Disconnect</button>
					</div>
				</form>
			</div>
			<div class="col-md-6">
				<form class="form-inline">
					<div class="form-group">
						<label for="name">What is your name?</label> <input type="text" id="name" class="form-control" placeholder="Your name here...">
						<div class="form-group">
							<label for="message">Input Message</label> <input type="text" id="chatMessage" class="form-control" placeholder="message.." />
						</div>
						<button id="chatSend" class="btn btn-default" type="button">Chat Send</button>
					</div>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<table id="conversation" class="table table-striped">
					<thead>
						<tr>
							<th>Greetings</th>
						</tr>
					</thead>
					<tbody id="greetings">
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var stompClient = null;

	function setConnected(connected) {
		$("#connect").prop("disabled", connected);
		$("#disconnect").prop("disabled", !connected);
		if (connected) {
			$("#conversation").show();
		} else {
			$("#conversation").hide();
		}
		$("#greetings").html("");
	}

	function connect() {
		var socket = new SockJS('/websocket');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			setConnected(true);
			stompClient.subscribe('/topic/checkDevice', function(result){
				console.log(result);
				console.log(JSON.parse(result.body));
			});
		})
	}
	
	function disconnect() {
		if (stompClient !== null) {
			stompClient.disconnect();
		}
		setConnected(false);
		console.log("Disconnected");
	}
	
	
	function sendChat() {
		stompClient.send('/app/checkDevice', {}, JSON.stringify({'name':$('#name').val(), 'msg':$('#chatMessage').val()}));
	}
	
	function sendRes() {
// 		stompClient.send('/app/checkDeviceResponse/192.168.0.121', {}, JSON.stringify({'data':{'name':$('#name').val(), 'msg':$('#chatMessage').val(), 'ip': '192.168.0.121'}}));
		stompClient.send('/app/checkDeviceResponse/192.168.0.121', {}, JSON.stringify({'data':{'imgSrc':'', 'name':$('#name').val(), 'msg':$('#chatMessage').val(), 'ip': '192.168.0.121'}}));
	}
	
	$(function() {
		$("form").on('submit', function(e) {
			e.preventDefault();
		});
		$("#connect").click(function() {
			connect();
		});
		$("#disconnect").click(function() {
			disconnect();
		});
		$("#send").click(function() {
			sendName();
		});
		
		$( "#chatSend" ).click(function(){ sendChat(); }); // ì¶ê°
	});
</script>
</html>