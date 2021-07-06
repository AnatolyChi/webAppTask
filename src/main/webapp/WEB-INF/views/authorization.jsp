<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String errorMessage = (String) request.getAttribute("message");
%>
<html>
    <head>
        <title>Authorization</title>
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
            <h1 style="text-align: center">Authorization</h1>
        </header>
        <p style="text-align: center">
            <%
                if (errorMessage != null)
                    out.println(errorMessage);
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
                <input type="hidden" name="command" value="AUTHORIZATION_USER">
                <input class="submit" type="submit" value="Submit">
            </form>
            <p>
                <button onclick="location.href='..'">Back to main</button>
            </p>
        </div>
    </body>
</html>
