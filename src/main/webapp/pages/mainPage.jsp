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
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.2.min.js" integrity="sha256-lZFHibXzMHo3GGeehn1hudTAP3Sc0uKXBXAzHX1sjtk=" crossorigin="anonymous"></script>
</head>
<body>
<div>
    <form id="loginForm" name="loginForm">
        <input type="hidden" name="type" value="LOGIN_CUSTOMER">
        <input type="hidden" name="sequence_id" value="">
        <input type="text" id="email" name="email">
        <input type="password" id="password" name="password">
        <input type="submit" value="Login"/>
    </form>
</div>

<%--<div>
    <form id="signUpForm" name="signUpForm">
        <input type="text" id="signUpEmail" name="email"/>
        <input type="password" id="signUpPassword" name="password"/>
        <input type="submit" id="signUp" value="signUp"/>
    </form>
</div>--%>

<div style="margin-bottom: 20px;">
    <span id="request" style="color: coral;"></span>
</div>

<div>
    <span id="response" style="color: cadetblue"></span>
</div>
</body>

<script type="text/javascript">
    var webSocket;

    function guid() {
        function s4() {
            return Math.floor((1 + Math.random()) * 0x10000)
                    .toString(16)
                    .substring(1);
        }
        return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
                s4() + '-' + s4() + s4() + s4();
    }

    function onMessage() {
        webSocket.onmessage = function (event) {
            var message = event.data;
            console.log("received message: " + message);
            $("#response").text(message);
        };
    }

    $(document).ready(function () {
        webSocket = new WebSocket("ws://localhost:8080/websocket");
        $("#loginForm").submit(function (e) {
            e.preventDefault();
            $("input[name=sequence_id]").val(guid());
            var parcel = JSON.parse('{"type":"LOGIN_CUSTOMER","sequence_id":"","data":{"email":"","password":""}}');
            parcel.sequence_id = guid();
            parcel.data.email = $("#email").val();
            parcel.data.password = $("#password").val();
            webSocket.send(JSON.stringify(parcel));
            var message = JSON.stringify(parcel, null, 4);
            console.log("message sent: " + message);
            $("#request").text(message);

            onMessage();

            return false;
        });

        $("#signUpForm").submit(function (e) {
            e.preventDefault();
            var parcel = JSON.parse('{"type":"SIGNUP_CUSTOMER","email":"","password":""}');
            parcel.email = $("#signUpEmail").val();
            parcel.password = $("#signUpPassword").val();
            webSocket.send(JSON.stringify(parcel));
            console.log("message sent: " + JSON.stringify(parcel));

            onMessage();

            return false;
        });
    });
</script>
</html>

