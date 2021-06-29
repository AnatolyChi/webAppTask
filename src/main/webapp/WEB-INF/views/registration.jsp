<%@ page import="com.example.webAppTask.beans.User" %>
<%@ page import="com.example.webAppTask.model.Model" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Registration</title>
        <link rel="stylesheet" href="../../resources/css/style.css">
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Comfortaa:wght@500&display=swap');
            h1, p {
                font-family: 'Comfortaa', serif;
            }
            body {
                background-image: url("../../resources/img/background-image.jpg");
                background-size: cover;
            }
            input:invalid { border: 2px solid skyblue; }
        </style>
    </head>
    <body>
        <header>
            <h1 style="text-align: center">Registration</h1>
        </header>
        <p style="text-align: center">
            <%
                if (Model.getInstance().isUser((User) request.getAttribute("user")))
                    out.println("A user with this name already exists!");
            %>
        </p>
        <div style="margin-left: 43%">
            <form action="/controller" method="POST">
                <label for="login">
                    Login:
                    <input id="login" class="login" name="login" required minlength="3">
                </label>
                <br>
                <label for="password">
                    Password:
                    <input id="password" class="pass" type="password" name="password" required minlength="5">
                </label>
                <br>
                <input type="hidden" name="command" value="REGISTRATION_NEW_USER">
                <input class="submit" type="submit" value="Submit">
            </form>
            <p>
                <button onclick="location.href='..'">Back to main</button>
            </p>
        </div>
    </body>
</html>
