<%@ page import="com.example.webAppTask.beans.User" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    User user = (User) request.getAttribute("user");
    String userWelcome = "Welcome ";
    String wrongMessage; //!!!

    if (user != null && !user.getLogin().isEmpty()) {
        userWelcome += user.getLogin();
    } else {
        userWelcome += "anonymous";
    }
%>
<html>
    <head>
        <title>Main Page</title>
        <link rel="stylesheet" href="resources/css/style.css">
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Comfortaa:wght@500&display=swap');
            h1 {
                font-family: 'Comfortaa', serif;
            }
            body {
                background-image: url('resources/img/background-image.jpg');
                background-size: cover;
            }
        </style>
    </head>
    <body>
        <header>
            <div>
                <img style="margin-top: 20px; margin-left: 20px" width="350" src="resources/img/News.svg.png" alt="news">
            </div>
            <div style="margin-top: 7px">
                <% if (user == null) { %>
                <form action="/controller" method="POST">
                    <input type="hidden" name="command" value="REGISTRATION">
                    <input class="reg" type="submit" value="Registration">
                </form>
                <form action="/controller" method="POST">
                    <input type="hidden" name="command" value="AUTHORIZATION">
                    <input class="auth" type="submit" value="Authorization">
                </form>
                <form action="/controller" method="GET">
                    <input type="hidden" name="command" value="REGISTERED_USERS">
                    <input class="auth" type="submit" value="Registered users">
                </form>
                <% } else { %>
                <form action="/controller" method="POST">
                    <input type="hidden" name="command" value="OUT_USER">
                    <input class="out-user" type="submit" value="Out_user">
                </form>
                <% } %>
            </div>
        </header>
        <div style="text-align: center">
            <h1><%= userWelcome %></h1>
            <p>Today: <%= new Date() %></p>
        </div>
    </body>
</html>