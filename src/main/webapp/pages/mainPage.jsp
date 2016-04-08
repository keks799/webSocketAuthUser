<%--
  Created by IntelliJ IDEA.
  User: Business_Book
  Date: 03.04.2016
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-1.12.2.min.js"
            integrity="sha256-lZFHibXzMHo3GGeehn1hudTAP3Sc0uKXBXAzHX1sjtk=" crossorigin="anonymous"></script>
</head>
<body>
<div>
    <form id="loginForm" name="loginForm">
        <input type="text" id="login" name="login"/>
        <input type="password" id="password" name="password"/>
        <input type="submit" value="Login"/>
    </form>
</div>

<div>
    <form id="signUpForm" name="signUpForm">
        <input type="text" id="signUpLogin" name="login"/>
        <input type="password" id="signUpPassword" name="password"/>
        <input type="submit" id="signUp" value="signUp"/>
    </form>
</div>
</body>

<script type="text/javascript">
    var webSocket;
    var output = document.getElementById("output");
    var connectBtn = document.getElementById("connectBtn");
    var sendBtn = document.getElementById("sendBtn");

    function connect() {
        // open the connection if one does not exist
        if (webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED) {
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

<script type="text/javascript">
    var webSocket;

    (function ($) {
        $.fn.serializeToJson = function () {

            var o = {};
            var a = this.serializeArray();
            $.each(a, function () {
                if (o[this.name]) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        };
    })(jQuery);

    $(document).ready(function () {
        webSocket = new WebSocket("ws://localhost:8080/auth");

        $("#loginForm").submit(function (e) {
            e.preventDefault();

            webSocket.send(JSON.stringify($("#loginForm").serializeToJson()));
            return false;
        });

        $("#signUpForm").submit(function (e) {
            e.preventDefault();
            webSocket = new WebSocket("ws://localhost:8080/create");
            webSocket.onopen = function (event) {
                webSocket.send(JSON.stringify($("#signUpForm").serializeToJson()));

                webSocket.onmessage = function (event) {
                    alert(event.data);
                };
            };
            return false;
        });

        webSocket.onmessage = function (event) {
            alert(event.data);
        };
    });
</script>
</html>

