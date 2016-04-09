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
    <%--<script type="text/javascript" src="/js/serializejson.js"></script>--%>
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

<div>
    <form id="signUpForm" name="signUpForm">
        <input type="text" id="signUpEmail" name="email"/>
        <input type="password" id="signUpPassword" name="password"/>
        <input type="submit" id="signUp" value="signUp"/>
    </form>
</div>
</body>

<script type="text/javascript">
    var webSocket;

//  answers
//  '{"type":"CUSTOMER_ERROR","sequence_id":"715c13b3-881a-9c97-b853-10be585a9747","data":{"error_description":"Customer not found","error_code":"customer.notFound"}}'

//  '{"type":"CUSTOMER_API_TOKEN","sequence_id":"cbf187c9-8679-0359-eb3d-c3211ee51a15","data":{"api_token":"afdd312c-3d2a-45ee-aa61-468aba3397f3","api_token_expiration_date":"2015-07-15T11:14:30Z"}}'

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
            console.log("received message: " + event.data);
        };
    }

    $(document).ready(function () {
        $("#loginForm").submit(function (e) {
            e.preventDefault();
            $("input[name=sequence_id]").val(guid());
            webSocket = new WebSocket("ws://localhost:8080/auth");
            webSocket.onopen = function (event) {
                var parcel = JSON.parse('{"type":"LOGIN_CUSTOMER","sequence_id":"","data":{"email":"","password":""}}');
                parcel.sequence_id = guid();
                parcel.data.email = $("#email").val();
                parcel.data.password = $("#password").val();
                webSocket.send(JSON.stringify(parcel));
                console.log("message sent: " + JSON.stringify(parcel));
            };

            onMessage();

            return false;
        });

        $("#signUpForm").submit(function (e) {
            e.preventDefault();
            webSocket = new WebSocket("ws://localhost:8080/create");
            webSocket.onopen = function (event) {
                var parcel = JSON.parse('{"email":"","password":""}');
                parcel.email = $("#signUpEmail").val();
                parcel.password = $("#signUpPassword").val();
                webSocket.send(JSON.stringify(parcel));
                console.log("message sent: " + JSON.stringify(parcel));
            };

            onMessage();

            return false;
        });
    });
</script>
</html>

