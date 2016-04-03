<%--
  Created by IntelliJ IDEA.
  User: Business_Book
  Date: 03.04.2016
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div>
    <input type="text" id="input"/>
</div>
<div>
    <input type="button" id="connectBtn" value="CONNECT"
           onclick="connect()"/> <input type="button" id="sendBtn"
                                        value="SEND" onclick="send()" disabled="true"/>
</div>
<div id="output">
    <p>Output</p>
</div>
</body>

<script type="text/javascript">
    var webSocket;
    var output = document.getElementById("output");
    var connectBtn = document.getElementById("connectBtn");
    var sendBtn = document.getElementById("sendBtn");

    function connect() {
        // oprn the connection if one does not exist
        if (webSocket !== undefined
                && webSocket.readyState !== WebSocket.CLOSED) {
            return;
        }
        // Create a websocket
        webSocket = new WebSocket("ws://localhost:8080/auth");

        webSocket.onopen = function (event) {
            updateOutput("Connected!");
            connectBtn.disabled = true;
            sendBtn.disabled = false;

        };

        webSocket.onmessage = function (event) {
            updateOutput(event.data);
        };

        webSocket.onclose = function (event) {
            updateOutput("Connection Closed");
            connectBtn.disabled = false;
            sendBtn.disabled = true;
        };
    }

    function send() {
        var text = document.getElementById("input").value;
        webSocket.send(text);
    }

    function closeSocket() {
        webSocket.close();
    }

    function updateOutput(text) {
        output.innerHTML += "<br/>" + text;
    }
</script>
</html>

